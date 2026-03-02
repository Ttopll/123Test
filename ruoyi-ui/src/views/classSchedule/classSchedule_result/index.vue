<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="任务 ID" prop="taskId">
        <el-input
          v-model="queryParams.taskId"
          placeholder="请输入任务 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="教师课程 ID" prop="teacherCourseId">
        <el-input
          v-model="queryParams.teacherCourseId"
          placeholder="请输入教师课程 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="教室 ID" prop="classroomId">
        <el-input
          v-model="queryParams.classroomId"
          placeholder="请输入教室 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="周次" prop="week">
        <el-input
          v-model="queryParams.week"
          placeholder="请输入周次"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="星期" prop="weekday">
        <el-input
          v-model="queryParams.weekday"
          placeholder="请输入星期"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="节次" prop="period">
        <el-input
          v-model="queryParams.period"
          placeholder="请输入节次"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上课开始时间" prop="startTime">
        <el-date-picker clearable
          v-model="queryParams.startTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择上课开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="上课结束时间" prop="endTime">
        <el-date-picker clearable
          v-model="queryParams.endTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择上课结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="选课学生人数" prop="studentCount">
        <el-input
          v-model="queryParams.studentCount"
          placeholder="请输入选课学生人数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否冲突" prop="isConflict">
        <el-input
          v-model="queryParams.isConflict"
          placeholder="请输入是否冲突"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更新用户" prop="updateUserId">
        <el-input
          v-model="queryParams.updateUserId"
          placeholder="请输入更新用户"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="删除状态" prop="isDeleted">
        <el-input
          v-model="queryParams.isDeleted"
          placeholder="请输入删除状态"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['classSchedule:classSchedule_result:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['classSchedule:classSchedule_result:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['classSchedule:classSchedule_result:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['classSchedule:classSchedule_result:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="classSchedule_resultList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="结果 ID" align="center" prop="resultId" />
      <el-table-column label="任务 ID" align="center" prop="taskId" />
      <el-table-column label="教师课程 ID" align="center" prop="teacherCourseId" />
      <el-table-column label="教室 ID" align="center" prop="classroomId" />
      <el-table-column label="周次" align="center" prop="week" />
      <el-table-column label="星期" align="center" prop="weekday" />
      <el-table-column label="节次" align="center" prop="period" />
      <el-table-column label="上课开始时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="上课结束时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="选课学生人数" align="center" prop="studentCount" />
      <el-table-column label="是否冲突" align="center" prop="isConflict" />
      <el-table-column label="冲突原因" align="center" prop="conflictReason" />
      <el-table-column label="结果状态" align="center" prop="status" />
      <el-table-column label="更新用户" align="center" prop="updateUserId" />
      <el-table-column label="删除状态" align="center" prop="isDeleted" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['classSchedule:classSchedule_result:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['classSchedule:classSchedule_result:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改排课结果对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="任务 ID" prop="taskId">
          <el-input v-model="form.taskId" placeholder="请输入任务 ID" />
        </el-form-item>
        <el-form-item label="教师课程 ID" prop="teacherCourseId">
          <el-input v-model="form.teacherCourseId" placeholder="请输入教师课程 ID" />
        </el-form-item>
        <el-form-item label="教室 ID" prop="classroomId">
          <el-input v-model="form.classroomId" placeholder="请输入教室 ID" />
        </el-form-item>
        <el-form-item label="周次" prop="week">
          <el-input v-model="form.week" placeholder="请输入周次" />
        </el-form-item>
        <el-form-item label="星期" prop="weekday">
          <el-input v-model="form.weekday" placeholder="请输入星期" />
        </el-form-item>
        <el-form-item label="节次" prop="period">
          <el-input v-model="form.period" placeholder="请输入节次" />
        </el-form-item>
        <el-form-item label="上课开始时间" prop="startTime">
          <el-date-picker clearable
            v-model="form.startTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择上课开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="上课结束时间" prop="endTime">
          <el-date-picker clearable
            v-model="form.endTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择上课结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="选课学生人数" prop="studentCount">
          <el-input v-model="form.studentCount" placeholder="请输入选课学生人数" />
        </el-form-item>
        <el-form-item label="是否冲突" prop="isConflict">
          <el-input v-model="form.isConflict" placeholder="请输入是否冲突" />
        </el-form-item>
        <el-form-item label="冲突原因" prop="conflictReason">
          <el-input v-model="form.conflictReason" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="更新用户" prop="updateUserId">
          <el-input v-model="form.updateUserId" placeholder="请输入更新用户" />
        </el-form-item>
        <el-form-item label="删除状态" prop="isDeleted">
          <el-input v-model="form.isDeleted" placeholder="请输入删除状态" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listClassSchedule_result, getClassSchedule_result, delClassSchedule_result, addClassSchedule_result, updateClassSchedule_result } from "@/api/classSchedule/classSchedule_result"

export default {
  name: "ClassSchedule_result",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 排课结果表格数据
      classSchedule_resultList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        taskId: null,
        teacherCourseId: null,
        classroomId: null,
        week: null,
        weekday: null,
        period: null,
        startTime: null,
        endTime: null,
        studentCount: null,
        isConflict: null,
        conflictReason: null,
        status: null,
        updateUserId: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        taskId: [
          { required: true, message: "任务 ID不能为空", trigger: "blur" }
        ],
        teacherCourseId: [
          { required: true, message: "教师课程 ID不能为空", trigger: "blur" }
        ],
        classroomId: [
          { required: true, message: "教室 ID不能为空", trigger: "blur" }
        ],
        week: [
          { required: true, message: "周次不能为空", trigger: "blur" }
        ],
        weekday: [
          { required: true, message: "星期不能为空", trigger: "blur" }
        ],
        period: [
          { required: true, message: "节次不能为空", trigger: "blur" }
        ],
        startTime: [
          { required: true, message: "上课开始时间不能为空", trigger: "blur" }
        ],
        endTime: [
          { required: true, message: "上课结束时间不能为空", trigger: "blur" }
        ],
        studentCount: [
          { required: true, message: "选课学生人数不能为空", trigger: "blur" }
        ],
        isConflict: [
          { required: true, message: "是否冲突不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "结果状态不能为空", trigger: "change" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "更新时间不能为空", trigger: "blur" }
        ],
        updateUserId: [
          { required: true, message: "更新用户不能为空", trigger: "blur" }
        ],
        isDeleted: [
          { required: true, message: "删除状态不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询排课结果列表 */
    getList() {
      this.loading = true
      listClassSchedule_result(this.queryParams).then(response => {
        this.classSchedule_resultList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        resultId: null,
        taskId: null,
        teacherCourseId: null,
        classroomId: null,
        week: null,
        weekday: null,
        period: null,
        startTime: null,
        endTime: null,
        studentCount: null,
        isConflict: null,
        conflictReason: null,
        status: null,
        createTime: null,
        updateTime: null,
        updateUserId: null,
        isDeleted: null
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.resultId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加排课结果"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const resultId = row.resultId || this.ids
      getClassSchedule_result(resultId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改排课结果"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.resultId != null) {
            updateClassSchedule_result(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addClassSchedule_result(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const resultIds = row.resultId || this.ids
      this.$modal.confirm('是否确认删除排课结果编号为"' + resultIds + '"的数据项？').then(function() {
        return delClassSchedule_result(resultIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('classSchedule/classSchedule_result/export', {
        ...this.queryParams
      }, `classSchedule_result_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
