<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="事务类型 ID" prop="affairTypeId">
        <el-input
          v-model="queryParams.affairTypeId"
          placeholder="请输入事务类型 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="提交人 ID" prop="applyUserId">
        <el-input
          v-model="queryParams.applyUserId"
          placeholder="请输入提交人 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="提交人角色" prop="applyUserRole">
        <el-input
          v-model="queryParams.applyUserRole"
          placeholder="请输入提交人角色"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="事务标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入事务标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="提交时间" prop="applyTime">
        <el-date-picker clearable
          v-model="queryParams.applyTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择提交时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="处理人 ID" prop="handleUserId">
        <el-input
          v-model="queryParams.handleUserId"
          placeholder="请输入处理人 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="超期时间" prop="expireTime">
        <el-date-picker clearable
          v-model="queryParams.expireTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择超期时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="撤回时间" prop="cancelTime">
        <el-date-picker clearable
          v-model="queryParams.cancelTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择撤回时间">
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
          v-hasPermi="['affair:affair_apply:add']"
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
          v-hasPermi="['affair:affair_apply:edit']"
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
          v-hasPermi="['affair:affair_apply:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['affair:affair_apply:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="affair_applyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="申请 ID" align="center" prop="applyId" />
      <el-table-column label="事务类型 ID" align="center" prop="affairTypeId" />
      <el-table-column label="提交人 ID" align="center" prop="applyUserId" />
      <el-table-column label="提交人角色" align="center" prop="applyUserRole" />
      <el-table-column label="事务标题" align="center" prop="title" />
      <el-table-column label="事务内容" align="center" prop="content" />
      <el-table-column label="附件 URL" align="center" prop="attachUrls" />
      <el-table-column label="签名图片 URL" align="center" prop="signatureUrl" />
      <el-table-column label="提交时间" align="center" prop="applyTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.applyTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="currentStatus" />
      <el-table-column label="处理人 ID" align="center" prop="handleUserId" />
      <el-table-column label="超期时间" align="center" prop="expireTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.expireTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="撤回原因" align="center" prop="cancelReason" />
      <el-table-column label="撤回时间" align="center" prop="cancelTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.cancelTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新用户" align="center" prop="updateUserId" />
      <el-table-column label="删除状态" align="center" prop="isDeleted" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['affair:affair_apply:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['affair:affair_apply:remove']"
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

    <!-- 添加或修改事务申请对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="事务类型 ID" prop="affairTypeId">
          <el-input v-model="form.affairTypeId" placeholder="请输入事务类型 ID" />
        </el-form-item>
        <el-form-item label="提交人 ID" prop="applyUserId">
          <el-input v-model="form.applyUserId" placeholder="请输入提交人 ID" />
        </el-form-item>
        <el-form-item label="提交人角色" prop="applyUserRole">
          <el-input v-model="form.applyUserRole" placeholder="请输入提交人角色" />
        </el-form-item>
        <el-form-item label="事务标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入事务标题" />
        </el-form-item>
        <el-form-item label="事务内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="提交时间" prop="applyTime">
          <el-date-picker clearable
            v-model="form.applyTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择提交时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="处理人 ID" prop="handleUserId">
          <el-input v-model="form.handleUserId" placeholder="请输入处理人 ID" />
        </el-form-item>
        <el-form-item label="超期时间" prop="expireTime">
          <el-date-picker clearable
            v-model="form.expireTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择超期时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="撤回原因" prop="cancelReason">
          <el-input v-model="form.cancelReason" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="撤回时间" prop="cancelTime">
          <el-date-picker clearable
            v-model="form.cancelTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择撤回时间">
          </el-date-picker>
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
import { listAffair_apply, getAffair_apply, delAffair_apply, addAffair_apply, updateAffair_apply } from "@/api/affair/affair_apply"

export default {
  name: "Affair_apply",
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
      // 事务申请表格数据
      affair_applyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        affairTypeId: null,
        applyUserId: null,
        applyUserRole: null,
        title: null,
        content: null,
        attachUrls: null,
        signatureUrl: null,
        applyTime: null,
        currentStatus: null,
        handleUserId: null,
        expireTime: null,
        cancelReason: null,
        cancelTime: null,
        updateUserId: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        affairTypeId: [
          { required: true, message: "事务类型 ID不能为空", trigger: "blur" }
        ],
        applyUserId: [
          { required: true, message: "提交人 ID不能为空", trigger: "blur" }
        ],
        applyUserRole: [
          { required: true, message: "提交人角色不能为空", trigger: "blur" }
        ],
        title: [
          { required: true, message: "事务标题不能为空", trigger: "blur" }
        ],
        content: [
          { required: true, message: "事务内容不能为空", trigger: "blur" }
        ],
        signatureUrl: [
          { required: true, message: "签名图片 URL不能为空", trigger: "blur" }
        ],
        applyTime: [
          { required: true, message: "提交时间不能为空", trigger: "blur" }
        ],
        currentStatus: [
          { required: true, message: "状态不能为空", trigger: "change" }
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
    /** 查询事务申请列表 */
    getList() {
      this.loading = true
      listAffair_apply(this.queryParams).then(response => {
        this.affair_applyList = response.rows
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
        applyId: null,
        affairTypeId: null,
        applyUserId: null,
        applyUserRole: null,
        title: null,
        content: null,
        attachUrls: null,
        signatureUrl: null,
        applyTime: null,
        currentStatus: null,
        handleUserId: null,
        expireTime: null,
        cancelReason: null,
        cancelTime: null,
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
      this.ids = selection.map(item => item.applyId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加事务申请"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const applyId = row.applyId || this.ids
      getAffair_apply(applyId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改事务申请"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.applyId != null) {
            updateAffair_apply(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addAffair_apply(this.form).then(response => {
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
      const applyIds = row.applyId || this.ids
      const updateUserId = row.applyUserId
      this.$modal.confirm('是否确认删除事务申请编号为"' + applyIds + '"的数据项？').then(function() {
        return delAffair_apply(applyIds, updateUserId)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('affair/affair_apply/export', {
        ...this.queryParams
      }, `affair_apply_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
