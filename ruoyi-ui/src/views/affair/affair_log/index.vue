<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="申请 ID" prop="applyId">
        <el-input
          v-model="queryParams.applyId"
          placeholder="请输入申请 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="处理人 ID" prop="handleUserId">
        <el-input
          v-model="queryParams.handleUserId"
          placeholder="请输入处理人 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="处理人姓名" prop="handleUserName">
        <el-input
          v-model="queryParams.handleUserName"
          placeholder="请输入处理人姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="处理人角色" prop="handleUserRole">
        <el-input
          v-model="queryParams.handleUserRole"
          placeholder="请输入处理人角色"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="处理时间" prop="handleTime">
        <el-date-picker clearable
          v-model="queryParams.handleTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择处理时间">
        </el-date-picker>
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
          v-hasPermi="['affair:affair_log:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click=""
          v-hasPermi="['affair:affair_log:edit']"
        >修改(已废除)</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['affair:affair_log:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['affair:affair_log:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="affair_logList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="日志 ID" align="center" prop="logId" />
      <el-table-column label="申请 ID" align="center" prop="applyId" />
      <el-table-column label="处理人 ID" align="center" prop="handleUserId" />
      <el-table-column label="处理人姓名" align="center" prop="handleUserName" />
      <el-table-column label="处理人角色" align="center" prop="handleUserRole" />
      <el-table-column label="处理前状态" align="center" prop="beforeStatus" />
      <el-table-column label="处理后状态" align="center" prop="afterStatus" />
      <el-table-column label="处理时间" align="center" prop="handleTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.handleTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="处理内容" align="center" prop="handleContent" />
      <el-table-column label="处理附件 URL" align="center" prop="handleAttachUrls" />
      <el-table-column label="删除状态" align="center" prop="isDeleted" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click=""
            v-hasPermi="['affair:affair_log:edit']"
          >修改(已废除)</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['affair:affair_log:remove']"
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

    <!-- 添加或修改事务处理日志对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="申请 ID" prop="applyId">
          <el-input v-model="form.applyId" placeholder="请输入申请 ID" />
        </el-form-item>
        <el-form-item label="处理人 ID" prop="handleUserId">
          <el-input v-model="form.handleUserId" placeholder="请输入处理人 ID" />
        </el-form-item>
        <el-form-item label="处理人姓名" prop="handleUserName">
          <el-input v-model="form.handleUserName" placeholder="请输入处理人姓名" />
        </el-form-item>
        <el-form-item label="处理人角色" prop="handleUserRole">
          <el-input v-model="form.handleUserRole" placeholder="请输入处理人角色" />
        </el-form-item>
        <el-form-item label="处理时间" prop="handleTime">
          <el-date-picker clearable
            v-model="form.handleTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择处理时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="处理内容">
          <editor v-model="form.handleContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="处理附件 URL" prop="handleAttachUrls">
          <el-input v-model="form.handleAttachUrls" type="textarea" placeholder="请输入内容" />
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
import { listAffair_log, getAffair_log, delAffair_log, addAffair_log } from "@/api/affair/affair_log"

export default {
  name: "Affair_log",
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
      // 事务处理日志表格数据
      affair_logList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        applyId: null,
        handleUserId: null,
        handleUserName: null,
        handleUserRole: null,
        beforeStatus: null,
        afterStatus: null,
        handleTime: null,
        handleContent: null,
        handleAttachUrls: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        applyId: [
          { required: true, message: "申请 ID不能为空", trigger: "blur" }
        ],
        handleUserId: [
          { required: true, message: "处理人 ID不能为空", trigger: "blur" }
        ],
        handleUserName: [
          { required: true, message: "处理人姓名不能为空", trigger: "blur" }
        ],
        handleUserRole: [
          { required: true, message: "处理人角色不能为空", trigger: "blur" }
        ],
        beforeStatus: [
          { required: true, message: "处理前状态不能为空", trigger: "change" }
        ],
        afterStatus: [
          { required: true, message: "处理后状态不能为空", trigger: "change" }
        ],
        handleTime: [
          { required: true, message: "处理时间不能为空", trigger: "blur" }
        ],
        handleContent: [
          { required: true, message: "处理内容不能为空", trigger: "blur" }
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
    /** 查询事务处理日志列表 */
    getList() {
      this.loading = true
      listAffair_log(this.queryParams).then(response => {
        this.affair_logList = response.rows
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
        logId: null,
        applyId: null,
        handleUserId: null,
        handleUserName: null,
        handleUserRole: null,
        beforeStatus: null,
        afterStatus: null,
        handleTime: null,
        handleContent: null,
        handleAttachUrls: null,
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
      this.ids = selection.map(item => item.logId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加事务处理日志"
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          addAffair_log(this.form).then(response => {
            this.$modal.msgSuccess("新增成功")
            this.open = false
            this.getList()
          })
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const logIds = row.logId || this.ids
      const updateUserId = row.handleUserId || this.ids
      this.$modal.confirm('是否确认删除事务处理日志编号为"' + logIds + '"的数据项？').then(function() {
        return delAffair_log(logIds, updateUserId)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('affair/affair_log/export', {
        ...this.queryParams
      }, `affair_log_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
