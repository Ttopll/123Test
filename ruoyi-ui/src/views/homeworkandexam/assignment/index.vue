<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="80px">
      <el-form-item label="课程ID" prop="courseId">
        <el-input
          v-model="queryParams.courseId"
          placeholder="请输入课程ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="教师ID" prop="teacherId">
        <el-input
          v-model="queryParams.teacherId"
          placeholder="请输入教师ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="作业标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入作业标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="作业类型" prop="assignmentType">
        <el-select v-model="queryParams.assignmentType" placeholder="请选择作业类型" clearable>
          <el-option label="个人作业" :value="1" />
          <el-option label="小组作业" :value="2" />
          <el-option label="实验报告" :value="3" />
          <el-option label="课程论文" :value="4" />
          <el-option label="项目作业" :value="5" />
        </el-select>
      </el-form-item>
      <el-form-item label="提交截止时间" prop="deadline">
        <el-date-picker clearable
                        v-model="queryParams.deadline"
                        type="datetime"
                        value-format="yyyy-MM-dd HH:mm:ss"
                        placeholder="请选择提交截止时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="总分" prop="totalScore">
        <el-input
          v-model="queryParams.totalScore"
          placeholder="请输入总分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option label="取消" :value="0" />
          <el-option label="发布" :value="1" />
          <el-option label="截止" :value="2" />
        </el-select>
      </el-form-item>
      <el-form-item label="更新用户" prop="updateUserId">
        <el-input
          v-model="queryParams.updateUserId"
          placeholder="请输入更新用户ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="删除状态" prop="isDeleted">
        <el-select v-model="queryParams.isDeleted" placeholder="请选择删除状态" clearable>
          <el-option label="正常" :value="0" />
          <el-option label="删除" :value="1" />
        </el-select>
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
          v-hasPermi="['homeworkandexam:assignment:add']"
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
          v-hasPermi="['homeworkandexam:assignment:edit']"
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
          v-hasPermi="['homeworkandexam:assignment:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['homeworkandexam:assignment:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="assignmentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="作业ID" align="center" prop="assignId" width="80" />
      <el-table-column label="课程ID" align="center" prop="courseId" width="80" />
      <el-table-column label="教师ID" align="center" prop="teacherId" width="100" />
      <el-table-column label="作业标题" align="center" prop="title" min-width="120" show-overflow-tooltip />
      <el-table-column label="作业类型" align="center" prop="assignmentType" width="100">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.assignmentType === 1" type="primary">个人作业</el-tag>
          <el-tag v-else-if="scope.row.assignmentType === 2" type="success">小组作业</el-tag>
          <el-tag v-else-if="scope.row.assignmentType === 3" type="warning">实验报告</el-tag>
          <el-tag v-else-if="scope.row.assignmentType === 4" type="info">课程论文</el-tag>
          <el-tag v-else-if="scope.row.assignmentType === 5" type="default">项目作业</el-tag>
          <span v-else>{{ scope.row.assignmentType }}</span>
        </template>
      </el-table-column>
      <el-table-column label="提交截止时间" align="center" prop="deadline" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.deadline, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="总分" align="center" prop="totalScore" width="80" />
      <el-table-column label="状态" align="center" prop="status" width="80">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status === 0" type="info">取消</el-tag>
          <el-tag v-else-if="scope.row.status === 1" type="success">发布</el-tag>
          <el-tag v-else-if="scope.row.status === 2" type="warning">截止</el-tag>
          <span v-else>{{ scope.row.status }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新用户" align="center" prop="updateUserId" width="100" />
      <el-table-column label="删除状态" align="center" prop="isDeleted" width="80">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.isDeleted === 0" type="success">正常</el-tag>
          <el-tag v-else-if="scope.row.isDeleted === 1" type="info">删除</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="150">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['homeworkandexam:assignment:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['homeworkandexam:assignment:remove']"
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

    <!-- 添加或修改作业管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="课程ID" prop="courseId">
          <el-input v-model="form.courseId" placeholder="请输入课程ID" type="number" />
        </el-form-item>
        <el-form-item label="教师ID" prop="teacherId">
          <el-input v-model="form.teacherId" placeholder="请输入教师ID" />
        </el-form-item>
        <el-form-item label="作业标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入作业标题" />
        </el-form-item>
        <el-form-item label="作业内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="作业类型" prop="assignmentType">
          <el-radio-group v-model="form.assignmentType">
            <el-radio :label="1">个人作业</el-radio>
            <el-radio :label="2">小组作业</el-radio>
            <el-radio :label="3">实验报告</el-radio>
            <el-radio :label="4">课程论文</el-radio>
            <el-radio :label="5">项目作业</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="提交截止时间" prop="deadline">
          <el-date-picker clearable
                          v-model="form.deadline"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="请选择提交截止时间"
                          style="width: 100%">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="总分" prop="totalScore">
          <el-input v-model="form.totalScore" placeholder="请输入总分" type="number" min="0" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio :label="0">取消</el-radio>
            <el-radio :label="1">发布</el-radio>
            <el-radio :label="2">截止</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="更新用户" prop="updateUserId">
          <el-input v-model="form.updateUserId" placeholder="请输入更新用户ID" />
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
import { listAssignment, getAssignment, delAssignment, addAssignment, updateAssignment } from "@/api/homeworkandexam/assignment"

export default {
  name: "Assignment",
  data() {
    // 验证数字类型
    const validateNumber = (rule, value, callback) => {
      if (value === '' || value === null || value === undefined) {
        callback(new Error('该字段不能为空'));
        return;
      }
      if (isNaN(Number(value))) {
        callback(new Error('请输入有效的数字'));
        return;
      }
      callback();
    };

    // 验证整数类型
    const validateInteger = (rule, value, callback) => {
      if (value === '' || value === null || value === undefined) {
        callback(new Error('该字段不能为空'));
        return;
      }
      if (isNaN(Number(value)) || !Number.isInteger(Number(value))) {
        callback(new Error('请输入有效的整数'));
        return;
      }
      if (Number(value) < 0) {
        callback(new Error('数值不能为负数'));
        return;
      }
      callback();
    };

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
      // 作业管理表格数据
      assignmentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        courseId: null,
        teacherId: null,
        title: null,
        content: null,
        attachUrl: null,
        deadline: null,
        assignmentType: null,
        totalScore: null,
        status: null,
        updateUserId: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        courseId: [
          { required: true, message: "课程ID不能为空", trigger: "blur" },
          { validator: validateNumber, trigger: "blur" }
        ],
        teacherId: [
          { required: true, message: "教师ID不能为空", trigger: "blur" }
        ],
        title: [
          { required: true, message: "作业标题不能为空", trigger: "blur" },
          { min: 1, max: 200, message: "长度在 1 到 200 个字符", trigger: "blur" }
        ],
        assignmentType: [
          { required: true, message: "作业类型不能为空", trigger: "change" }
        ],
        deadline: [
          { required: true, message: "提交截止时间不能为空", trigger: "change" }
        ],
        totalScore: [
          { required: true, message: "总分不能为空", trigger: "blur" },
          { validator: validateInteger, trigger: "blur" }
        ],
        status: [
          { required: true, message: "状态不能为空", trigger: "change" }
        ],
        updateUserId: [
          { required: true, message: "更新用户不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询作业管理列表 */
    getList() {
      this.loading = true
      listAssignment(this.queryParams).then(response => {
        this.assignmentList = response.rows
        this.total = response.total
        this.loading = false
      }).catch(() => {
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
        assignId: null,
        courseId: null,
        teacherId: null,
        title: null,
        content: null,
        attachUrl: null,
        deadline: null,
        assignmentType: 1, // 默认个人作业
        totalScore: null,
        status: 1, // 默认发布状态
        updateUserId: null,
        isDeleted: 0
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
      this.ids = selection.map(item => item.assignId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加作业管理"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const assignId = row.assignId || this.ids[0]
      getAssignment(assignId).then(response => {
        this.form = response.data
        // 确保数字字段类型正确
        if (this.form.assignmentType) this.form.assignmentType = Number(this.form.assignmentType)
        if (this.form.status) this.form.status = Number(this.form.status)
        if (this.form.isDeleted) this.form.isDeleted = Number(this.form.isDeleted)
        if (this.form.courseId) this.form.courseId = Number(this.form.courseId)
        if (this.form.totalScore) this.form.totalScore = Number(this.form.totalScore)

        this.open = true
        this.title = "修改作业管理"
      }).catch(error => {
        console.error("获取作业信息失败:", error)
        this.$modal.msgError("获取作业信息失败")
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          // 数据类型转换
          const submitData = {
            ...this.form,
            assignmentType: Number(this.form.assignmentType),
            status: Number(this.form.status),
            isDeleted: Number(this.form.isDeleted || 0)
          }

          // 转换ID字段为数字
          if (this.form.courseId) submitData.courseId = Number(this.form.courseId)
          if (this.form.totalScore) submitData.totalScore = Number(this.form.totalScore)

          if (submitData.assignId != null) {
            updateAssignment(submitData).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            }).catch(error => {
              console.error("修改失败:", error)
              this.$modal.msgError("修改失败")
            })
          } else {
            addAssignment(submitData).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            }).catch(error => {
              console.error("新增失败:", error)
              this.$modal.msgError("新增失败")
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const assignIds = row.assignId || this.ids
      this.$modal.confirm('是否确认删除作业管理编号为"' + assignIds + '"的数据项？').then(() => {
        return delAssignment(assignIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('homeworkandexam/assignment/export', {
        ...this.queryParams
      }, `assignment_${new Date().getTime()}.xlsx`)
    },
    // 时间格式化方法
    parseTime(time, cFormat) {
      if (!time) return ''
      if (arguments.length === 0) {
        return null
      }
      const format = cFormat || '{y}-{m}-{d} {h}:{i}:{s}'
      let date
      if (typeof time === 'object') {
        date = time
      } else {
        if ((typeof time === 'string') && (/^[0-9]+$/.test(time))) {
          time = parseInt(time)
        }
        if ((typeof time === 'number') && (time.toString().length === 10)) {
          time = time * 1000
        }
        date = new Date(time)
      }
      const formatObj = {
        y: date.getFullYear(),
        m: date.getMonth() + 1,
        d: date.getDate(),
        h: date.getHours(),
        i: date.getMinutes(),
        s: date.getSeconds(),
        a: date.getDay()
      }
      const time_str = format.replace(/{([ymdhisa])+}/g, (result, key) => {
        const value = formatObj[key]
        if (key === 'a') { return ['日', '一', '二', '三', '四', '五', '六'][value] }
        return value.toString().padStart(2, '0')
      })
      return time_str
    }
  }
}
</script>

<style scoped>
.app-container {
  padding: 20px;
}
.mb8 {
  margin-bottom: 8px;
}
</style>
