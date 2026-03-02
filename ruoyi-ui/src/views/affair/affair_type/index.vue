<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="类型名称" prop="typeName">
        <el-input
          v-model="queryParams.typeName"
          placeholder="请输入类型名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类型编码" prop="typeCode">
        <el-input
          v-model="queryParams.typeCode"
          placeholder="请输入类型编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="允许提交角色" prop="applyRole">
        <el-input
          v-model="queryParams.applyRole"
          placeholder="请输入允许提交角色"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="负责部门" prop="handleDept">
        <el-input
          v-model="queryParams.handleDept"
          placeholder="请输入负责部门"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="负责角色" prop="handleRole">
        <el-input
          v-model="queryParams.handleRole"
          placeholder="请输入负责角色"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="处理时限" prop="handleLimit">
        <el-input
          v-model="queryParams.handleLimit"
          placeholder="请输入处理时限"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="排序序号" prop="sort">
        <el-input
          v-model="queryParams.sort"
          placeholder="请输入排序序号"
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
          v-hasPermi="['affair:affair_type:add']"
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
          v-hasPermi="['affair:affair_type:edit']"
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
          v-hasPermi="['affair:affair_type:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['affair:affair_type:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="affair_typeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="类型 ID" align="center" prop="affairTypeId" />
      <el-table-column label="类型名称" align="center" prop="typeName" />
      <el-table-column label="类型编码" align="center" prop="typeCode" />
      <el-table-column label="允许提交角色" align="center" prop="applyRole" />
      <el-table-column label="负责部门" align="center" prop="handleDept" />
      <el-table-column label="负责角色" align="center" prop="handleRole" />
      <el-table-column label="处理时限" align="center" prop="handleLimit" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="排序序号" align="center" prop="sort" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="删除状态" align="center" prop="isDeleted" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['affair:affair_type:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['affair:affair_type:remove']"
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

    <!-- 添加或修改事务类型对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="类型名称" prop="typeName">
          <el-input v-model="form.typeName" placeholder="请输入类型名称" />
        </el-form-item>
        <el-form-item label="类型编码" prop="typeCode">
          <el-input v-model="form.typeCode" placeholder="请输入类型编码" />
        </el-form-item>
        <el-form-item label="允许提交角色" prop="applyRole">
          <el-input v-model="form.applyRole" placeholder="请输入允许提交角色" />
        </el-form-item>
        <el-form-item label="负责部门" prop="handleDept">
          <el-input v-model="form.handleDept" placeholder="请输入负责部门" />
        </el-form-item>
        <el-form-item label="负责角色" prop="handleRole">
          <el-input v-model="form.handleRole" placeholder="请输入负责角色" />
        </el-form-item>
        <el-form-item label="处理时限" prop="handleLimit">
          <el-input v-model="form.handleLimit" placeholder="请输入处理时限" />
        </el-form-item>
        <el-form-item label="排序序号" prop="sort">
          <el-input v-model="form.sort" placeholder="请输入排序序号" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
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
import { listAffair_type, getAffair_type, delAffair_type, addAffair_type, updateAffair_type } from "@/api/affair/affair_type"

export default {
  name: "Affair_type",
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
      // 事务类型表格数据
      affair_typeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        typeName: null,
        typeCode: null,
        applyRole: null,
        handleDept: null,
        handleRole: null,
        handleLimit: null,
        status: null,
        sort: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        typeName: [
          { required: true, message: "类型名称不能为空", trigger: "blur" }
        ],
        typeCode: [
          { required: true, message: "类型编码不能为空", trigger: "blur" }
        ],
        applyRole: [
          { required: true, message: "允许提交角色不能为空", trigger: "blur" }
        ],
        handleDept: [
          { required: true, message: "负责部门不能为空", trigger: "blur" }
        ],
        handleRole: [
          { required: true, message: "负责角色不能为空", trigger: "blur" }
        ],
        handleLimit: [
          { required: true, message: "处理时限不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "状态不能为空", trigger: "change" }
        ],
        sort: [
          { required: true, message: "排序序号不能为空", trigger: "blur" }
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
    /** 查询事务类型列表 */
    getList() {
      this.loading = true
      listAffair_type(this.queryParams).then(response => {
        this.affair_typeList = response.rows
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
        affairTypeId: null,
        typeName: null,
        typeCode: null,
        applyRole: null,
        handleDept: null,
        handleRole: null,
        handleLimit: null,
        status: null,
        sort: null,
        remark: null,
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
      this.ids = selection.map(item => item.affairTypeId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加事务类型"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const affairTypeId = row.affairTypeId || this.ids
      getAffair_type(affairTypeId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改事务类型"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.affairTypeId != null) {
            updateAffair_type(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addAffair_type(this.form).then(response => {
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
      const affairTypeIds = row.affairTypeId || this.ids
      const updateUserId = 10001 // 占位符，需调整
      this.$modal.confirm('是否确认删除事务类型编号为"' + affairTypeIds + '"的数据项？').then(function() {
        return delAffair_type(affairTypeIds, updateUserId)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('affair/affair_type/export', {
        ...this.queryParams
      }, `affair_type_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
