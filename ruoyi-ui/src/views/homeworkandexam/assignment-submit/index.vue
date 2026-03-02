<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="80px">
      <el-form-item label="作业ID" prop="assignId">
        <el-input
          v-model="queryParams.assignId"
          placeholder="请输入作业ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学生ID" prop="studentId">
        <el-input
          v-model="queryParams.studentId"
          placeholder="请输入学生ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="批改状态" prop="correctStatus">
        <el-select v-model="queryParams.correctStatus" placeholder="请选择批改状态" clearable>
          <el-option label="未批" :value="0" />
          <el-option label="已批" :value="1" />
          <el-option label="退回" :value="2" />
        </el-select>
      </el-form-item>
      <el-form-item label="提交文件URL" prop="fileUrl">
        <el-input
          v-model="queryParams.fileUrl"
          placeholder="请输入提交文件URL"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="提交时间" prop="submitTime">
        <el-date-picker clearable
                        v-model="queryParams.submitTime"
                        type="datetime"
                        value-format="yyyy-MM-dd HH:mm:ss"
                        placeholder="请选择提交时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="得分" prop="score">
        <el-input
          v-model="queryParams.score"
          placeholder="请输入得分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="批改时间" prop="correctTime">
        <el-date-picker clearable
                        v-model="queryParams.correctTime"
                        type="datetime"
                        value-format="yyyy-MM-dd HH:mm:ss"
                        placeholder="请选择批改时间">
        </el-date-picker>
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
          v-hasPermi="['homeworkandexam:assignment-submit:add']"
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
          v-hasPermi="['homeworkandexam:assignment-submit:edit']"
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
          v-hasPermi="['homeworkandexam:assignment-submit:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['homeworkandexam:assignment-submit:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="assignmentSubmitList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="提交ID" align="center" prop="submitId" width="80" />
      <el-table-column label="作业ID" align="center" prop="assignId" width="80" />
      <el-table-column label="学生ID" align="center" prop="studentId" width="100" />
      <el-table-column label="提交内容" align="center" prop="content" min-width="150" show-overflow-tooltip />
      <el-table-column label="提交文件URL" align="center" prop="fileUrl" min-width="120" show-overflow-tooltip />
      <el-table-column label="批改状态" align="center" prop="correctStatus" width="100">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.correctStatus === 0" type="info">未批</el-tag>
          <el-tag v-else-if="scope.row.correctStatus === 1" type="success">已批</el-tag>
          <el-tag v-else-if="scope.row.correctStatus === 2" type="warning">退回</el-tag>
          <span v-else>{{ scope.row.correctStatus }}</span>
        </template>
      </el-table-column>
      <el-table-column label="提交时间" align="center" prop="submitTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.submitTime, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="得分" align="center" prop="score" width="80" />
      <el-table-column label="批改备注" align="center" prop="correctRemark" min-width="120" show-overflow-tooltip />
      <el-table-column label="批改时间" align="center" prop="correctTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.correctTime, '{y}-{m}-{d} {h}:{i}') }}</span>
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
            v-hasPermi="['homeworkandexam:assignment-submit:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['homeworkandexam:assignment-submit:remove']"
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

    <!-- 添加或修改作业提交/批改对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="作业ID" prop="assignId">
          <el-input v-model="form.assignId" placeholder="请输入作业ID" type="number" />
        </el-form-item>
        <el-form-item label="学生ID" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学生ID" />
        </el-form-item>
        <el-form-item label="提交内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="提交文件URL" prop="fileUrl">
          <el-input v-model="form.fileUrl" placeholder="请输入提交文件URL" />
        </el-form-item>
        <el-form-item label="批改状态" prop="correctStatus">
          <el-radio-group v-model="form.correctStatus">
            <el-radio :label="0">未批</el-radio>
            <el-radio :label="1">已批</el-radio>
            <el-radio :label="2">退回</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="提交时间" prop="submitTime">
          <el-date-picker clearable
                          v-model="form.submitTime"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="请选择提交时间"
                          style="width: 100%">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="得分" prop="score">
          <el-input v-model="form.score" placeholder="请输入得分" type="number" min="0" />
        </el-form-item>
        <el-form-item label="批改备注" prop="correctRemark">
          <el-input v-model="form.correctRemark" type="textarea" placeholder="请输入批改备注" :rows="3" />
        </el-form-item>
        <el-form-item label="批改时间" prop="correctTime">
          <el-date-picker clearable
                          v-model="form.correctTime"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="请选择批改时间"
                          style="width: 100%">
          </el-date-picker>
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
import { listAssignmentSubmit, getAssignmentSubmit, delAssignmentSubmit, addAssignmentSubmit, updateAssignmentSubmit } from "@/api/homeworkandexam/assignment-submit"

export default {
  name: "AssignmentSubmit",
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
      // 作业提交/批改表格数据
      assignmentSubmitList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        assignId: null,
        studentId: null,
        content: null,
        fileUrl: null,
        submitTime: null,
        correctStatus: null,
        score: null,
        correctRemark: null,
        correctTime: null,
        updateUserId: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        assignId: [
          { required: true, message: "作业ID不能为空", trigger: "blur" },
          { validator: validateNumber, trigger: "blur" }
        ],
        studentId: [
          { required: true, message: "学生ID不能为空", trigger: "blur" }
        ],
        submitTime: [
          { required: true, message: "提交时间不能为空", trigger: "change" }
        ],
        correctStatus: [
          { required: true, message: "批改状态不能为空", trigger: "change" }
        ],
        score: [
          { validator: validateInteger, trigger: "blur" }
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
    /** 查询作业提交/批改列表 */
    getList() {
      this.loading = true
      listAssignmentSubmit(this.queryParams).then(response => {
        this.assignmentSubmitList = response.rows
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
        submitId: null,
        assignId: null,
        studentId: null,
        content: null,
        fileUrl: null,
        submitTime: null,
        correctStatus: 0, // 默认未批
        score: null,
        correctRemark: null,
        correctTime: null,
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
      this.ids = selection.map(item => item.submitId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加作业提交/批改"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const submitId = row.submitId || this.ids[0]
      getAssignmentSubmit(submitId).then(response => {
        this.form = response.data
        // 确保数字字段类型正确
        if (this.form.correctStatus) this.form.correctStatus = Number(this.form.correctStatus)
        if (this.form.isDeleted) this.form.isDeleted = Number(this.form.isDeleted)
        if (this.form.assignId) this.form.assignId = Number(this.form.assignId)
        if (this.form.score) this.form.score = Number(this.form.score)

        this.open = true
        this.title = "修改作业提交/批改"
      }).catch(error => {
        console.error("获取作业提交信息失败:", error)
        this.$modal.msgError("获取作业提交信息失败")
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          // 数据类型转换
          const submitData = {
            ...this.form,
            correctStatus: Number(this.form.correctStatus),
            isDeleted: Number(this.form.isDeleted || 0)
          }

          // 转换ID字段为数字
          if (this.form.assignId) submitData.assignId = Number(this.form.assignId)
          if (this.form.score) submitData.score = Number(this.form.score)

          if (submitData.submitId != null) {
            updateAssignmentSubmit(submitData).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            }).catch(error => {
              console.error("修改失败:", error)
              this.$modal.msgError("修改失败")
            })
          } else {
            addAssignmentSubmit(submitData).then(response => {
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
      const submitIds = row.submitId || this.ids
      this.$modal.confirm('是否确认删除作业提交/批改编号为"' + submitIds + '"的数据项？').then(() => {
        return delAssignmentSubmit(submitIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('homeworkandexam/assignment-submit/export', {
        ...this.queryParams
      }, `assignment-submit_${new Date().getTime()}.xlsx`)
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
