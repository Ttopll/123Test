<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="评价名称" prop="evaluationName">
        <el-input
          v-model="queryParams.evaluationName"
          placeholder="请输入评价名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学期" prop="semester">
        <el-input
          v-model="queryParams.semester"
          placeholder="请输入学期"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="评价开始时间" prop="startTime">
        <el-date-picker clearable
          v-model="queryParams.startTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择评价开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="评价结束时间" prop="endTime">
        <el-date-picker clearable
          v-model="queryParams.endTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择评价结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="评价发起角色" prop="targetRole">
        <el-input
          v-model="queryParams.targetRole"
          placeholder="请输入评价发起角色"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="被评价角色" prop="evaluatedRole">
        <el-input
          v-model="queryParams.evaluatedRole"
          placeholder="请输入被评价角色"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否匿名" prop="isAnonymous">
        <el-input
          v-model="queryParams.isAnonymous"
          placeholder="请输入是否匿名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否需审核" prop="isAuditRequired">
        <el-input
          v-model="queryParams.isAuditRequired"
          placeholder="请输入是否需审核"
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
      <el-form-item label="更新用户" prop="updateUserId">
        <el-input
          v-model="queryParams.updateUserId"
          placeholder="请输入更新用户"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="isDeleted">
        <el-input
          v-model="queryParams.isDeleted"
          placeholder="请输入状态"
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
          v-hasPermi="['evaluate:evaluate_config:add']"
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
          v-hasPermi="['evaluate:evaluate_config:edit']"
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
          v-hasPermi="['evaluate:evaluate_config:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['evaluate:evaluate_config:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="evaluate_configList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="配置 ID" align="center" prop="configId" />
      <el-table-column label="评价名称" align="center" prop="evaluationName" />
      <el-table-column label="学期" align="center" prop="semester" />
      <el-table-column label="评价开始时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="评价结束时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="评价发起角色" align="center" prop="targetRole" />
      <el-table-column label="被评价角色" align="center" prop="evaluatedRole" />
      <el-table-column label="评价维度" align="center" prop="evaluationDimensions" />
      <el-table-column label="评分规则" align="center" prop="scoreRule" />
      <el-table-column label="是否匿名" align="center" prop="isAnonymous" />
      <el-table-column label="是否需审核" align="center" prop="isAuditRequired" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="创建人 ID" align="center" prop="createUserId" />
      <el-table-column label="更新用户" align="center" prop="updateUserId" />
      <el-table-column label="状态" align="center" prop="isDeleted" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['evaluate:evaluate_config:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['evaluate:evaluate_config:remove']"
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

    <!-- 添加或修改教师评价配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="评价名称" prop="evaluationName">
          <el-input v-model="form.evaluationName" placeholder="请输入评价名称" />
        </el-form-item>
        <el-form-item label="学期" prop="semester">
          <el-input v-model="form.semester" placeholder="请输入学期" />
        </el-form-item>
        <el-form-item label="评价开始时间" prop="startTime">
          <el-date-picker clearable
            v-model="form.startTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择评价开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="评价结束时间" prop="endTime">
          <el-date-picker clearable
            v-model="form.endTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择评价结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="评价发起角色" prop="targetRole">
          <el-input v-model="form.targetRole" placeholder="请输入评价发起角色" />
        </el-form-item>
        <el-form-item label="被评价角色" prop="evaluatedRole">
          <el-input v-model="form.evaluatedRole" placeholder="请输入被评价角色" />
        </el-form-item>
        <el-form-item label="评价维度" prop="evaluationDimensions">
          <el-input v-model="form.evaluationDimensions" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="评分规则" prop="scoreRule">
          <el-input v-model="form.scoreRule" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="是否匿名" prop="isAnonymous">
          <el-input v-model="form.isAnonymous" placeholder="请输入是否匿名" />
        </el-form-item>
        <el-form-item label="是否需审核" prop="isAuditRequired">
          <el-input v-model="form.isAuditRequired" placeholder="请输入是否需审核" />
        </el-form-item>
        <el-form-item label="创建人 ID" prop="createUserId">
          <el-input v-model="form.createUserId" placeholder="请输入创建人 ID" />
        </el-form-item>
        <el-form-item label="更新用户" prop="updateUserId">
          <el-input v-model="form.updateUserId" placeholder="请输入更新用户" />
        </el-form-item>
        <el-form-item label="状态" prop="isDeleted">
          <el-input v-model="form.isDeleted" placeholder="请输入状态" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
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
import { listEvaluate_config, getEvaluate_config, delEvaluate_config, addEvaluate_config, updateEvaluate_config } from "@/api/evaluate/evaluate_config"

export default {
  name: "Evaluate_config",
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
      // 教师评价配置表格数据
      evaluate_configList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        evaluationName: null,
        semester: null,
        startTime: null,
        endTime: null,
        targetRole: null,
        evaluatedRole: null,
        evaluationDimensions: null,
        scoreRule: null,
        isAnonymous: null,
        isAuditRequired: null,
        status: null,
        createUserId: null,
        updateUserId: null,
        isDeleted: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        evaluationName: [
          { required: true, message: "评价名称不能为空", trigger: "blur" }
        ],
        semester: [
          { required: true, message: "学期不能为空", trigger: "blur" }
        ],
        startTime: [
          { required: true, message: "评价开始时间不能为空", trigger: "blur" }
        ],
        endTime: [
          { required: true, message: "评价结束时间不能为空", trigger: "blur" }
        ],
        targetRole: [
          { required: true, message: "评价发起角色不能为空", trigger: "blur" }
        ],
        evaluatedRole: [
          { required: true, message: "被评价角色不能为空", trigger: "blur" }
        ],
        evaluationDimensions: [
          { required: true, message: "评价维度不能为空", trigger: "blur" }
        ],
        scoreRule: [
          { required: true, message: "评分规则不能为空", trigger: "blur" }
        ],
        isAnonymous: [
          { required: true, message: "是否匿名不能为空", trigger: "blur" }
        ],
        isAuditRequired: [
          { required: true, message: "是否需审核不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "状态不能为空", trigger: "change" }
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
          { required: true, message: "状态不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询教师评价配置列表 */
    getList() {
      this.loading = true
      listEvaluate_config(this.queryParams).then(response => {
        this.evaluate_configList = response.rows
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
        configId: null,
        evaluationName: null,
        semester: null,
        startTime: null,
        endTime: null,
        targetRole: null,
        evaluatedRole: null,
        evaluationDimensions: null,
        scoreRule: null,
        isAnonymous: null,
        isAuditRequired: null,
        status: null,
        createUserId: null,
        createTime: null,
        updateTime: null,
        updateUserId: null,
        isDeleted: null,
        remark: null
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
      this.ids = selection.map(item => item.configId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加教师评价配置"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const configId = row.configId || this.ids
      getEvaluate_config(configId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改教师评价配置"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.configId != null) {
            updateEvaluate_config(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addEvaluate_config(this.form).then(response => {
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
      const configIds = row.configId || this.ids
      this.$modal.confirm('是否确认删除教师评价配置编号为"' + configIds + '"的数据项？').then(function() {
        return delEvaluate_config(configIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('evaluate/evaluate_config/export', {
        ...this.queryParams
      }, `evaluate_config_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
