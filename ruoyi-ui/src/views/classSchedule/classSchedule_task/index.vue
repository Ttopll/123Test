<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="任务名称" prop="taskName">
        <el-input
          v-model="queryParams.taskName"
          placeholder="请输入任务名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="排课学期" prop="semester">
        <el-input
          v-model="queryParams.semester"
          placeholder="请输入排课学期"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="排课院系 ID" prop="deptId">
        <el-input
          v-model="queryParams.deptId"
          placeholder="请输入排课院系 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="排课年级" prop="grade">
        <el-input
          v-model="queryParams.grade"
          placeholder="请输入排课年级"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="排课周期开始日期" prop="startDate">
        <el-date-picker clearable
          v-model="queryParams.startDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择排课周期开始日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="排课周期结束日期" prop="endDate">
        <el-date-picker clearable
          v-model="queryParams.endDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择排课周期结束日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="关联规则 ID" prop="ruleIds">
        <el-input
          v-model="queryParams.ruleIds"
          placeholder="请输入关联规则 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="冲突数量" prop="conflictCount">
        <el-input
          v-model="queryParams.conflictCount"
          placeholder="请输入冲突数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建人 ID" prop="createUserId">
        <el-input
          v-model="queryParams.createUserId"
          placeholder="请输入创建人 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="执行时间" prop="executeTime">
        <el-date-picker clearable
          v-model="queryParams.executeTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择执行时间">
        </el-date-picker>
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
          v-hasPermi="['classSchedule:classSchedule_task:add']"
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
          v-hasPermi="['classSchedule:classSchedule_task:edit']"
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
          v-hasPermi="['classSchedule:classSchedule_task:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['classSchedule:classSchedule_task:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="classSchedule_taskList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="任务 ID" align="center" prop="taskId" />
      <el-table-column label="任务名称" align="center" prop="taskName" />
      <el-table-column label="排课学期" align="center" prop="semester" />
      <el-table-column label="排课院系 ID" align="center" prop="deptId" />
      <el-table-column label="排课年级" align="center" prop="grade" />
      <el-table-column label="排课周期开始日期" align="center" prop="startDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="排课周期结束日期" align="center" prop="endDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="关联规则 ID" align="center" prop="ruleIds" />
      <el-table-column label="任务状态" align="center" prop="taskStatus" />
      <el-table-column label="冲突数量" align="center" prop="conflictCount" />
      <el-table-column label="创建人 ID" align="center" prop="createUserId" />
      <el-table-column label="执行时间" align="center" prop="executeTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.executeTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="更新用户" align="center" prop="updateUserId" />
      <el-table-column label="删除状态" align="center" prop="isDeleted" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['classSchedule:classSchedule_task:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['classSchedule:classSchedule_task:remove']"
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

    <!-- 添加或修改排课任务对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="任务名称" prop="taskName">
          <el-input v-model="form.taskName" placeholder="请输入任务名称" />
        </el-form-item>
        <el-form-item label="排课学期" prop="semester">
          <el-input v-model="form.semester" placeholder="请输入排课学期" />
        </el-form-item>
        <el-form-item label="排课院系 ID" prop="deptId">
          <el-input v-model="form.deptId" placeholder="请输入排课院系 ID" />
        </el-form-item>
        <el-form-item label="排课年级" prop="grade">
          <el-input v-model="form.grade" placeholder="请输入排课年级" />
        </el-form-item>
        <el-form-item label="排课周期开始日期" prop="startDate">
          <el-date-picker clearable
            v-model="form.startDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择排课周期开始日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="排课周期结束日期" prop="endDate">
          <el-date-picker clearable
            v-model="form.endDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择排课周期结束日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="关联规则 ID" prop="ruleIds">
          <el-input v-model="form.ruleIds" placeholder="请输入关联规则 ID" />
        </el-form-item>
        <el-form-item label="冲突数量" prop="conflictCount">
          <el-input v-model="form.conflictCount" placeholder="请输入冲突数量" />
        </el-form-item>
        <el-form-item label="创建人 ID" prop="createUserId">
          <el-input v-model="form.createUserId" placeholder="请输入创建人 ID" />
        </el-form-item>
        <el-form-item label="执行时间" prop="executeTime">
          <el-date-picker clearable
            v-model="form.executeTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择执行时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
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
import { listClassSchedule_task, getClassSchedule_task, delClassSchedule_task, addClassSchedule_task, updateClassSchedule_task } from "@/api/classSchedule/classSchedule_task"

export default {
  name: "ClassSchedule_task",
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
      // 排课任务表格数据
      classSchedule_taskList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        taskName: null,
        semester: null,
        deptId: null,
        grade: null,
        startDate: null,
        endDate: null,
        ruleIds: null,
        taskStatus: null,
        conflictCount: null,
        createUserId: null,
        executeTime: null,
        updateUserId: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        taskName: [
          { required: true, message: "任务名称不能为空", trigger: "blur" }
        ],
        semester: [
          { required: true, message: "排课学期不能为空", trigger: "blur" }
        ],
        startDate: [
          { required: true, message: "排课周期开始日期不能为空", trigger: "blur" }
        ],
        endDate: [
          { required: true, message: "排课周期结束日期不能为空", trigger: "blur" }
        ],
        ruleIds: [
          { required: true, message: "关联规则 ID不能为空", trigger: "blur" }
        ],
        taskStatus: [
          { required: true, message: "任务状态不能为空", trigger: "change" }
        ],
        conflictCount: [
          { required: true, message: "冲突数量不能为空", trigger: "blur" }
        ],
        createUserId: [
          { required: true, message: "创建人 ID不能为空", trigger: "blur" }
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
    /** 查询排课任务列表 */
    getList() {
      this.loading = true
      listClassSchedule_task(this.queryParams).then(response => {
        this.classSchedule_taskList = response.rows
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
        taskId: null,
        taskName: null,
        semester: null,
        deptId: null,
        grade: null,
        startDate: null,
        endDate: null,
        ruleIds: null,
        taskStatus: null,
        conflictCount: null,
        createUserId: null,
        executeTime: null,
        remark: null,
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
      this.ids = selection.map(item => item.taskId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加排课任务"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const taskId = row.taskId || this.ids
      getClassSchedule_task(taskId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改排课任务"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.taskId != null) {
            updateClassSchedule_task(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addClassSchedule_task(this.form).then(response => {
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
      const taskIds = row.taskId || this.ids
      this.$modal.confirm('是否确认删除排课任务编号为"' + taskIds + '"的数据项？').then(function() {
        return delClassSchedule_task(taskIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('classSchedule/classSchedule_task/export', {
        ...this.queryParams
      }, `classSchedule_task_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
