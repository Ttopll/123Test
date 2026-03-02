<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="学生 ID" prop="studentId">
        <el-input
          v-model="queryParams.studentId"
          placeholder="请输入学生 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关联简历 ID" prop="resumeId">
        <el-input
          v-model="queryParams.resumeId"
          placeholder="请输入关联简历 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="投递企业 ID" prop="enterpriseId">
        <el-input
          v-model="queryParams.enterpriseId"
          placeholder="请输入投递企业 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="投递岗位 ID" prop="positionId">
        <el-input
          v-model="queryParams.positionId"
          placeholder="请输入投递岗位 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="投递时间" prop="deliveryTime">
        <el-date-picker clearable
          v-model="queryParams.deliveryTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择投递时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="企业反馈时间" prop="feedbackTime">
        <el-date-picker clearable
          v-model="queryParams.feedbackTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择企业反馈时间">
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
          v-hasPermi="['employ:employ_delivery:add']"
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
          v-hasPermi="['employ:employ_delivery:edit']"
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
          v-hasPermi="['employ:employ_delivery:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['employ:employ_delivery:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="employ_deliveryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="投递记录 ID" align="center" prop="deliveryId" />
      <el-table-column label="学生 ID" align="center" prop="studentId" />
      <el-table-column label="关联简历 ID" align="center" prop="resumeId" />
      <el-table-column label="投递企业 ID" align="center" prop="enterpriseId" />
      <el-table-column label="投递岗位 ID" align="center" prop="positionId" />
      <el-table-column label="投递时间" align="center" prop="deliveryTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.deliveryTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="投递状态" align="center" prop="deliveryStatus" />
      <el-table-column label="企业反馈内容" align="center" prop="feedbackContent" />
      <el-table-column label="企业反馈时间" align="center" prop="feedbackTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.feedbackTime, '{y}-{m}-{d}') }}</span>
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
            v-hasPermi="['employ:employ_delivery:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['employ:employ_delivery:remove']"
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

    <!-- 添加或修改简历投递记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学生 ID" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学生 ID" />
        </el-form-item>
        <el-form-item label="关联简历 ID" prop="resumeId">
          <el-input v-model="form.resumeId" placeholder="请输入关联简历 ID" />
        </el-form-item>
        <el-form-item label="投递企业 ID" prop="enterpriseId">
          <el-input v-model="form.enterpriseId" placeholder="请输入投递企业 ID" />
        </el-form-item>
        <el-form-item label="投递岗位 ID" prop="positionId">
          <el-input v-model="form.positionId" placeholder="请输入投递岗位 ID" />
        </el-form-item>
        <el-form-item label="投递时间" prop="deliveryTime">
          <el-date-picker clearable
            v-model="form.deliveryTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择投递时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="企业反馈内容">
          <editor v-model="form.feedbackContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="企业反馈时间" prop="feedbackTime">
          <el-date-picker clearable
            v-model="form.feedbackTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择企业反馈时间">
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
import { listEmploy_delivery, getEmploy_delivery, delEmploy_delivery, addEmploy_delivery, updateEmploy_delivery } from "@/api/employ/employ_delivery"

export default {
  name: "Employ_delivery",
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
      // 简历投递记录表格数据
      employ_deliveryList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        studentId: null,
        resumeId: null,
        enterpriseId: null,
        positionId: null,
        deliveryTime: null,
        deliveryStatus: null,
        feedbackContent: null,
        feedbackTime: null,
        updateUserId: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        studentId: [
          { required: true, message: "学生 ID不能为空", trigger: "blur" }
        ],
        resumeId: [
          { required: true, message: "关联简历 ID不能为空", trigger: "blur" }
        ],
        enterpriseId: [
          { required: true, message: "投递企业 ID不能为空", trigger: "blur" }
        ],
        positionId: [
          { required: true, message: "投递岗位 ID不能为空", trigger: "blur" }
        ],
        deliveryTime: [
          { required: true, message: "投递时间不能为空", trigger: "blur" }
        ],
        deliveryStatus: [
          { required: true, message: "投递状态不能为空", trigger: "change" }
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
    /** 查询简历投递记录列表 */
    getList() {
      this.loading = true
      listEmploy_delivery(this.queryParams).then(response => {
        this.employ_deliveryList = response.rows
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
        deliveryId: null,
        studentId: null,
        resumeId: null,
        enterpriseId: null,
        positionId: null,
        deliveryTime: null,
        deliveryStatus: null,
        feedbackContent: null,
        feedbackTime: null,
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
      this.ids = selection.map(item => item.deliveryId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加简历投递记录"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const deliveryId = row.deliveryId || this.ids
      getEmploy_delivery(deliveryId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改简历投递记录"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.deliveryId != null) {
            updateEmploy_delivery(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addEmploy_delivery(this.form).then(response => {
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
      const deliveryIds = row.deliveryId || this.ids
      this.$modal.confirm('是否确认删除简历投递记录编号为"' + deliveryIds + '"的数据项？').then(function() {
        return delEmploy_delivery(deliveryIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('employ/employ_delivery/export', {
        ...this.queryParams
      }, `employ_delivery_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
