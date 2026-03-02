<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="企业全称" prop="enterpriseName">
        <el-input
          v-model="queryParams.enterpriseName"
          placeholder="请输入企业全称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="企业简称" prop="enterpriseShort">
        <el-input
          v-model="queryParams.enterpriseShort"
          placeholder="请输入企业简称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="细分行业" prop="industryDetail">
        <el-input
          v-model="queryParams.industryDetail"
          placeholder="请输入细分行业"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="企业规模" prop="enterpriseScale">
        <el-input
          v-model="queryParams.enterpriseScale"
          placeholder="请输入企业规模"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所在省份" prop="province">
        <el-input
          v-model="queryParams.province"
          placeholder="请输入所在省份"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所在城市" prop="city">
        <el-input
          v-model="queryParams.city"
          placeholder="请输入所在城市"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="详细地址" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入详细地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="对接人姓名" prop="contactPerson">
        <el-input
          v-model="queryParams.contactPerson"
          placeholder="请输入对接人姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="对接人电话" prop="contactPhone">
        <el-input
          v-model="queryParams.contactPhone"
          placeholder="请输入对接人电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="对接人邮箱" prop="contactEmail">
        <el-input
          v-model="queryParams.contactEmail"
          placeholder="请输入对接人邮箱"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="合作等级" prop="cooperationLevel">
        <el-input
          v-model="queryParams.cooperationLevel"
          placeholder="请输入合作等级"
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
          v-hasPermi="['employ:employ_enterprise:add']"
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
          v-hasPermi="['employ:employ_enterprise:edit']"
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
          v-hasPermi="['employ:employ_enterprise:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['employ:employ_enterprise:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="employ_enterpriseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="企业 ID" align="center" prop="enterpriseId" />
      <el-table-column label="企业全称" align="center" prop="enterpriseName" />
      <el-table-column label="企业简称" align="center" prop="enterpriseShort" />
      <el-table-column label="所属行业" align="center" prop="industryType" />
      <el-table-column label="细分行业" align="center" prop="industryDetail" />
      <el-table-column label="企业规模" align="center" prop="enterpriseScale" />
      <el-table-column label="所在省份" align="center" prop="province" />
      <el-table-column label="所在城市" align="center" prop="city" />
      <el-table-column label="详细地址" align="center" prop="address" />
      <el-table-column label="对接人姓名" align="center" prop="contactPerson" />
      <el-table-column label="对接人电话" align="center" prop="contactPhone" />
      <el-table-column label="对接人邮箱" align="center" prop="contactEmail" />
      <el-table-column label="招聘状态" align="center" prop="recruitStatus" />
      <el-table-column label="合作等级" align="center" prop="cooperationLevel" />
      <el-table-column label="合作状态" align="center" prop="cooperationStatus" />
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
            v-hasPermi="['employ:employ_enterprise:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['employ:employ_enterprise:remove']"
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

    <!-- 添加或修改就业合作企业对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="企业全称" prop="enterpriseName">
          <el-input v-model="form.enterpriseName" placeholder="请输入企业全称" />
        </el-form-item>
        <el-form-item label="企业简称" prop="enterpriseShort">
          <el-input v-model="form.enterpriseShort" placeholder="请输入企业简称" />
        </el-form-item>
        <el-form-item label="细分行业" prop="industryDetail">
          <el-input v-model="form.industryDetail" placeholder="请输入细分行业" />
        </el-form-item>
        <el-form-item label="企业规模" prop="enterpriseScale">
          <el-input v-model="form.enterpriseScale" placeholder="请输入企业规模" />
        </el-form-item>
        <el-form-item label="所在省份" prop="province">
          <el-input v-model="form.province" placeholder="请输入所在省份" />
        </el-form-item>
        <el-form-item label="所在城市" prop="city">
          <el-input v-model="form.city" placeholder="请输入所在城市" />
        </el-form-item>
        <el-form-item label="详细地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入详细地址" />
        </el-form-item>
        <el-form-item label="对接人姓名" prop="contactPerson">
          <el-input v-model="form.contactPerson" placeholder="请输入对接人姓名" />
        </el-form-item>
        <el-form-item label="对接人电话" prop="contactPhone">
          <el-input v-model="form.contactPhone" placeholder="请输入对接人电话" />
        </el-form-item>
        <el-form-item label="对接人邮箱" prop="contactEmail">
          <el-input v-model="form.contactEmail" placeholder="请输入对接人邮箱" />
        </el-form-item>
        <el-form-item label="合作等级" prop="cooperationLevel">
          <el-input v-model="form.cooperationLevel" placeholder="请输入合作等级" />
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
import { listEmploy_enterprise, getEmploy_enterprise, delEmploy_enterprise, addEmploy_enterprise, updateEmploy_enterprise } from "@/api/employ/employ_enterprise"

export default {
  name: "Employ_enterprise",
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
      // 就业合作企业表格数据
      employ_enterpriseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        enterpriseName: null,
        enterpriseShort: null,
        industryType: null,
        industryDetail: null,
        enterpriseScale: null,
        province: null,
        city: null,
        address: null,
        contactPerson: null,
        contactPhone: null,
        contactEmail: null,
        recruitStatus: null,
        cooperationLevel: null,
        cooperationStatus: null,
        updateUserId: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        enterpriseName: [
          { required: true, message: "企业全称不能为空", trigger: "blur" }
        ],
        industryType: [
          { required: true, message: "所属行业不能为空", trigger: "change" }
        ],
        enterpriseScale: [
          { required: true, message: "企业规模不能为空", trigger: "blur" }
        ],
        province: [
          { required: true, message: "所在省份不能为空", trigger: "blur" }
        ],
        city: [
          { required: true, message: "所在城市不能为空", trigger: "blur" }
        ],
        contactPerson: [
          { required: true, message: "对接人姓名不能为空", trigger: "blur" }
        ],
        contactPhone: [
          { required: true, message: "对接人电话不能为空", trigger: "blur" }
        ],
        recruitStatus: [
          { required: true, message: "招聘状态不能为空", trigger: "change" }
        ],
        cooperationLevel: [
          { required: true, message: "合作等级不能为空", trigger: "blur" }
        ],
        cooperationStatus: [
          { required: true, message: "合作状态不能为空", trigger: "change" }
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
    /** 查询就业合作企业列表 */
    getList() {
      this.loading = true
      listEmploy_enterprise(this.queryParams).then(response => {
        this.employ_enterpriseList = response.rows
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
        enterpriseId: null,
        enterpriseName: null,
        enterpriseShort: null,
        industryType: null,
        industryDetail: null,
        enterpriseScale: null,
        province: null,
        city: null,
        address: null,
        contactPerson: null,
        contactPhone: null,
        contactEmail: null,
        recruitStatus: null,
        cooperationLevel: null,
        cooperationStatus: null,
        createTime: null,
        remark: null,
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
      this.ids = selection.map(item => item.enterpriseId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加就业合作企业"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const enterpriseId = row.enterpriseId || this.ids
      getEmploy_enterprise(enterpriseId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改就业合作企业"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.enterpriseId != null) {
            updateEmploy_enterprise(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addEmploy_enterprise(this.form).then(response => {
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
      const enterpriseIds = row.enterpriseId || this.ids
      this.$modal.confirm('是否确认删除就业合作企业编号为"' + enterpriseIds + '"的数据项？').then(function() {
        return delEmploy_enterprise(enterpriseIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('employ/employ_enterprise/export', {
        ...this.queryParams
      }, `employ_enterprise_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
