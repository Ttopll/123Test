<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="企业ID" prop="enterpriseId">
        <el-input
          v-model="queryParams.enterpriseId"
          placeholder="请输入企业ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="岗位名称" prop="positionName">
        <el-input
          v-model="queryParams.positionName"
          placeholder="请输入岗位名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="薪资范围" prop="salaryRange">
        <el-input
          v-model="queryParams.salaryRange"
          placeholder="请输入薪资范围"
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
      <el-form-item label="招聘人数" prop="recruitCount">
        <el-input
          v-model="queryParams.recruitCount"
          placeholder="请输入招聘人数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="招聘截止日期" prop="deadline">
        <el-date-picker clearable
          v-model="queryParams.deadline"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择招聘截止日期">
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
          v-hasPermi="['employ:employ_position:add']"
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
          v-hasPermi="['employ:employ_position:edit']"
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
          v-hasPermi="['employ:employ_position:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['employ:employ_position:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="employ_positionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="岗位ID" align="center" prop="positionId" />
      <el-table-column label="企业ID" align="center" prop="enterpriseId" />
      <el-table-column label="岗位名称" align="center" prop="positionName" />
      <el-table-column label="薪资范围" align="center" prop="salaryRange" />
      <el-table-column label="细分行业" align="center" prop="industryDetail" />
      <el-table-column label="岗位要求" align="center" prop="jobRequirement" />
      <el-table-column label="招聘人数" align="center" prop="recruitCount" />
      <el-table-column label="招聘截止日期" align="center" prop="deadline" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.deadline, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="更新用户" align="center" prop="updateUserId" />
      <el-table-column label="删除状态" align="center" prop="isDeleted" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['employ:employ_position:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['employ:employ_position:remove']"
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

    <!-- 添加或修改招聘岗位对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="企业ID" prop="enterpriseId">
          <el-input v-model="form.enterpriseId" placeholder="请输入企业ID" />
        </el-form-item>
        <el-form-item label="岗位名称" prop="positionName">
          <el-input v-model="form.positionName" placeholder="请输入岗位名称" />
        </el-form-item>
        <el-form-item label="薪资范围" prop="salaryRange">
          <el-input v-model="form.salaryRange" placeholder="请输入薪资范围" />
        </el-form-item>
        <el-form-item label="细分行业" prop="industryDetail">
          <el-input v-model="form.industryDetail" placeholder="请输入细分行业" />
        </el-form-item>
        <el-form-item label="岗位要求" prop="jobRequirement">
          <el-input v-model="form.jobRequirement" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="招聘人数" prop="recruitCount">
          <el-input v-model="form.recruitCount" placeholder="请输入招聘人数" />
        </el-form-item>
        <el-form-item label="招聘截止日期" prop="deadline">
          <el-date-picker clearable
            v-model="form.deadline"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择招聘截止日期">
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
import { listEmploy_position, getEmploy_position, delEmploy_position, addEmploy_position, updateEmploy_position } from "@/api/employ/employ_position"

export default {
  name: "Employ_position",
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
      // 招聘岗位表格数据
      employ_positionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        enterpriseId: null,
        positionName: null,
        salaryRange: null,
        industryDetail: null,
        jobRequirement: null,
        recruitCount: null,
        deadline: null,
        status: null,
        updateUserId: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        enterpriseId: [
          { required: true, message: "企业ID不能为空", trigger: "blur" }
        ],
        positionName: [
          { required: true, message: "岗位名称不能为空", trigger: "blur" }
        ],
        salaryRange: [
          { required: true, message: "薪资范围不能为空", trigger: "blur" }
        ],
        recruitCount: [
          { required: true, message: "招聘人数不能为空", trigger: "blur" }
        ],
        deadline: [
          { required: true, message: "招聘截止日期不能为空", trigger: "blur" }
        ],
        status: [
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
    /** 查询招聘岗位列表 */
    getList() {
      this.loading = true
      listEmploy_position(this.queryParams).then(response => {
        this.employ_positionList = response.rows
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
        positionId: null,
        enterpriseId: null,
        positionName: null,
        salaryRange: null,
        industryDetail: null,
        jobRequirement: null,
        recruitCount: null,
        deadline: null,
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
      this.ids = selection.map(item => item.positionId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加招聘岗位"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const positionId = row.positionId || this.ids
      getEmploy_position(positionId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改招聘岗位"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.positionId != null) {
            updateEmploy_position(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addEmploy_position(this.form).then(response => {
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
      const positionIds = row.positionId || this.ids
      this.$modal.confirm('是否确认删除招聘岗位编号为"' + positionIds + '"的数据项？').then(function() {
        return delEmploy_position(positionIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('employ/employ_position/export', {
        ...this.queryParams
      }, `employ_position_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
