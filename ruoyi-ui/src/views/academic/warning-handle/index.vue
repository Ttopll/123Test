<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="120px">
      <el-form-item label="关联预警ID" prop="warningId">
        <el-input
          v-model="queryParams.warningId"
          placeholder="请输入关联预警ID"
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
      <el-form-item label="处理人ID" prop="handlerId">
        <el-input
          v-model="queryParams.handlerId"
          placeholder="请输入处理人ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="处理类型" prop="handleType">
        <el-select v-model="queryParams.handleType" placeholder="请选择处理类型" clearable>
          <el-option label="心理辅导" :value="1" />
          <el-option label="学业指导" :value="2" />
          <el-option label="行为矫正" :value="3" />
          <el-option label="家校沟通" :value="4" />
          <el-option label="其他处理" :value="5" />
        </el-select>
      </el-form-item>
      <el-form-item label="处理状态" prop="handleStatus">
        <el-select v-model="queryParams.handleStatus" placeholder="请选择处理状态" clearable>
          <el-option label="待处理" :value="0" />
          <el-option label="处理中" :value="1" />
          <el-option label="已完成" :value="2" />
          <el-option label="已关闭" :value="3" />
        </el-select>
      </el-form-item>
      <el-form-item label="处理事项标题" prop="handleTitle">
        <el-input
          v-model="queryParams.handleTitle"
          placeholder="请输入处理事项标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="处理时间" prop="handleTime">
        <el-date-picker clearable
                        v-model="queryParams.handleTime"
                        type="daterange"
                        range-separator="至"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期"
                        value-format="yyyy-MM-dd">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="完成时间" prop="completeTime">
        <el-date-picker clearable
                        v-model="queryParams.completeTime"
                        type="daterange"
                        range-separator="至"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期"
                        value-format="yyyy-MM-dd">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="审核人ID" prop="verifyUserId">
        <el-input
          v-model="queryParams.verifyUserId"
          placeholder="请输入审核人ID"
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
          v-hasPermi="['academic:warning-handle:add']"
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
          v-hasPermi="['academic:warning-handle:edit']"
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
          v-hasPermi="['academic:warning-handle:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['academic:warning-handle:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="warningHandleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="处理记录ID" align="center" prop="handleId" width="90" />
      <el-table-column label="预警ID" align="center" prop="warningId" width="80" />
      <el-table-column label="学生ID" align="center" prop="studentId" width="100" />
      <el-table-column label="处理人ID" align="center" prop="handlerId" width="100" />
      <el-table-column label="处理类型" align="center" prop="handleType" width="100">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.handleType === 1" type="primary">心理辅导</el-tag>
          <el-tag v-else-if="scope.row.handleType === 2" type="success">学业指导</el-tag>
          <el-tag v-else-if="scope.row.handleType === 3" type="warning">行为矫正</el-tag>
          <el-tag v-else-if="scope.row.handleType === 4" type="info">家校沟通</el-tag>
          <el-tag v-else-if="scope.row.handleType === 5">其他处理</el-tag>
          <span v-else>{{ scope.row.handleType }}</span>
        </template>
      </el-table-column>
      <el-table-column label="处理事项标题" align="center" prop="handleTitle" min-width="120" show-overflow-tooltip />
      <el-table-column label="处理状态" align="center" prop="handleStatus" width="100">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.handleStatus === 0" type="warning">待处理</el-tag>
          <el-tag v-else-if="scope.row.handleStatus === 1" type="primary">处理中</el-tag>
          <el-tag v-else-if="scope.row.handleStatus === 2" type="success">已完成</el-tag>
          <el-tag v-else-if="scope.row.handleStatus === 3" type="info">已关闭</el-tag>
          <span v-else>{{ scope.row.handleStatus }}</span>
        </template>
      </el-table-column>
      <el-table-column label="处理时间" align="center" prop="handleTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.handleTime, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="完成时间" align="center" prop="completeTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.completeTime, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核人ID" align="center" prop="verifyUserId" width="100" />
      <el-table-column label="审核时间" align="center" prop="verifyTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.verifyTime, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="150">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['academic:warning-handle:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['academic:warning-handle:remove']"
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

    <!-- 添加或修改预警处理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="关联预警ID" prop="warningId">
              <el-input v-model="form.warningId" placeholder="请输入关联预警ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="学生ID" prop="studentId">
              <el-input v-model="form.studentId" placeholder="请输入学生ID" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="处理人ID" prop="handlerId">
              <el-input v-model="form.handlerId" placeholder="请输入处理人ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="处理类型" prop="handleType">
              <el-select v-model="form.handleType" placeholder="请选择处理类型" style="width: 100%">
                <el-option label="心理辅导" :value="1" />
                <el-option label="学业指导" :value="2" />
                <el-option label="行为矫正" :value="3" />
                <el-option label="家校沟通" :value="4" />
                <el-option label="其他处理" :value="5" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="处理状态" prop="handleStatus">
              <el-select v-model="form.handleStatus" placeholder="请选择处理状态" style="width: 100%">
                <el-option label="待处理" :value="0" />
                <el-option label="处理中" :value="1" />
                <el-option label="已完成" :value="2" />
                <el-option label="已关闭" :value="3" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="更新用户" prop="updateUserId">
              <el-input v-model="form.updateUserId" placeholder="请输入更新用户ID" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="处理事项标题" prop="handleTitle">
          <el-input v-model="form.handleTitle" placeholder="请输入处理事项标题" />
        </el-form-item>

        <el-form-item label="处理内容" prop="handleContent">
          <el-input v-model="form.handleContent" type="textarea" placeholder="请输入处理内容" :rows="4" />
        </el-form-item>

        <el-row>
          <el-col :span="12">
            <el-form-item label="处理时间" prop="handleTime">
              <el-date-picker clearable
                              v-model="form.handleTime"
                              type="datetime"
                              value-format="yyyy-MM-dd HH:mm:ss"
                              placeholder="请选择处理时间"
                              style="width: 100%">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="完成时间" prop="completeTime">
              <el-date-picker clearable
                              v-model="form.completeTime"
                              type="datetime"
                              value-format="yyyy-MM-dd HH:mm:ss"
                              placeholder="请选择完成时间"
                              style="width: 100%">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="审核人ID" prop="verifyUserId">
              <el-input v-model="form.verifyUserId" placeholder="请输入审核人ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="审核时间" prop="verifyTime">
              <el-date-picker clearable
                              v-model="form.verifyTime"
                              type="datetime"
                              value-format="yyyy-MM-dd HH:mm:ss"
                              placeholder="请选择审核时间"
                              style="width: 100%">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="审核备注" prop="verifyRemark">
          <el-input v-model="form.verifyRemark" type="textarea" placeholder="请输入审核备注" :rows="2" />
        </el-form-item>

        <el-form-item label="处理附件URL" prop="attachUrl">
          <el-input v-model="form.attachUrl" placeholder="请输入处理附件URL" />
        </el-form-item>

        <el-form-item label="补充备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入补充备注" :rows="2" />
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
import { listWarningHandle, getWarningHandle, delWarningHandle, addWarningHandle, updateWarningHandle } from "@/api/academic/warning-handle"

export default {
  name: "WarningHandle",
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
      // 预警处理表格数据
      warningHandleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        warningId: null,
        studentId: null,
        handlerId: null,
        handleType: null,
        handleTitle: null,
        handleContent: null,
        handleStatus: null,
        handleTime: null,
        completeTime: null,
        verifyUserId: null,
        verifyTime: null,
        verifyRemark: null,
        attachUrl: null,
        updateUserId: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        warningId: [
          { required: true, message: "关联预警ID不能为空", trigger: "blur" },
          { validator: validateNumber, trigger: "blur" }
        ],
        studentId: [
          { required: true, message: "学生ID不能为空", trigger: "blur" },
          { validator: validateNumber, trigger: "blur" }
        ],
        handlerId: [
          { required: true, message: "处理人ID不能为空", trigger: "blur" },
          { validator: validateNumber, trigger: "blur" }
        ],
        handleType: [
          { required: true, message: "处理类型不能为空", trigger: "change" }
        ],
        handleTitle: [
          { required: true, message: "处理事项标题不能为空", trigger: "blur" },
          { min: 1, max: 100, message: "长度在 1 到 100 个字符", trigger: "blur" }
        ],
        handleContent: [
          { required: true, message: "处理内容不能为空", trigger: "blur" }
        ],
        handleStatus: [
          { required: true, message: "处理状态不能为空", trigger: "change" }
        ],
        handleTime: [
          { required: true, message: "处理时间不能为空", trigger: "change" }
        ],
        updateUserId: [
          { required: true, message: "更新用户不能为空", trigger: "blur" },
          { validator: validateNumber, trigger: "blur" }
        ],
        verifyUserId: [
          { validator: validateNumber, trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询预警处理列表 */
    getList() {
      this.loading = true
      // 处理时间范围查询
      const params = {
        ...this.queryParams,
        handleTimeBegin: this.queryParams.handleTime && this.queryParams.handleTime[0],
        handleTimeEnd: this.queryParams.handleTime && this.queryParams.handleTime[1],
        completeTimeBegin: this.queryParams.completeTime && this.queryParams.completeTime[0],
        completeTimeEnd: this.queryParams.completeTime && this.queryParams.completeTime[1]
      }
      // 移除原始的时间范围字段
      delete params.handleTime
      delete params.completeTime

      listWarningHandle(params).then(response => {
        this.warningHandleList = response.rows
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
        handleId: null,
        warningId: null,
        studentId: null,
        handlerId: null,
        handleType: 1, // 默认处理类型
        handleTitle: '',
        handleContent: '',
        handleStatus: 0, // 默认待处理
        handleTime: null,
        completeTime: null,
        verifyUserId: null,
        verifyTime: null,
        verifyRemark: '',
        attachUrl: '',
        remark: '',
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
      this.queryParams = {
        pageNum: 1,
        pageSize: 10,
        warningId: null,
        studentId: null,
        handlerId: null,
        handleType: null,
        handleTitle: null,
        handleContent: null,
        handleStatus: null,
        handleTime: null,
        completeTime: null,
        verifyUserId: null,
        verifyTime: null,
        verifyRemark: null,
        attachUrl: null,
        updateUserId: null,
        isDeleted: null
      }
      this.getList()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.handleId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加预警处理"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const handleId = row.handleId || this.ids[0]
      getWarningHandle(handleId).then(response => {
        this.form = response.data
        // 确保数字字段类型正确
        if (this.form.handleType) this.form.handleType = Number(this.form.handleType)
        if (this.form.handleStatus) this.form.handleStatus = Number(this.form.handleStatus)
        if (this.form.isDeleted) this.form.isDeleted = Number(this.form.isDeleted)

        this.open = true
        this.title = "修改预警处理"
      }).catch(error => {
        console.error("获取处理记录失败:", error)
        this.$modal.msgError("获取处理记录失败")
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          // 数据类型转换
          const submitData = {
            ...this.form,
            handleType: Number(this.form.handleType),
            handleStatus: Number(this.form.handleStatus),
            isDeleted: Number(this.form.isDeleted || 0)
          }

          // 转换ID字段为数字
          if (this.form.warningId) submitData.warningId = Number(this.form.warningId)
          if (this.form.studentId) submitData.studentId = Number(this.form.studentId)
          if (this.form.handlerId) submitData.handlerId = Number(this.form.handlerId)
          if (this.form.updateUserId) submitData.updateUserId = Number(this.form.updateUserId)
          if (this.form.verifyUserId) submitData.verifyUserId = Number(this.form.verifyUserId)

          if (submitData.handleId != null) {
            updateWarningHandle(submitData).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            }).catch(error => {
              console.error("修改失败:", error)
              this.$modal.msgError("修改失败")
            })
          } else {
            addWarningHandle(submitData).then(response => {
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
      const handleIds = row.handleId || this.ids
      this.$modal.confirm('是否确认删除预警处理编号为"' + handleIds + '"的数据项？').then(() => {
        return delWarningHandle(handleIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      const params = {
        ...this.queryParams,
        handleTimeBegin: this.queryParams.handleTime && this.queryParams.handleTime[0],
        handleTimeEnd: this.queryParams.handleTime && this.queryParams.handleTime[1],
        completeTimeBegin: this.queryParams.completeTime && this.queryParams.completeTime[0],
        completeTimeEnd: this.queryParams.completeTime && this.queryParams.completeTime[1]
      }
      delete params.handleTime
      delete params.completeTime

      this.download('academic/warning-handle/export', params, `warning-handle_${new Date().getTime()}.xlsx`)
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
.el-table .warning-row {
  background: oldlace;
}
.el-table .success-row {
  background: #f0f9eb;
}
</style>
