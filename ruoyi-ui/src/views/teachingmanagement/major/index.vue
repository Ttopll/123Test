<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="80px">
      <el-form-item label="专业名称" prop="majorName">
        <el-input
          v-model="queryParams.majorName"
          placeholder="请输入专业名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="专业编码" prop="majorCode">
        <el-input
          v-model="queryParams.majorCode"
          placeholder="请输入专业编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关联院系ID" prop="deptId">
        <el-input
          v-model="queryParams.deptId"
          placeholder="请输入关联院系ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="培养层次" prop="trainingLevel">
        <el-select v-model="queryParams.trainingLevel" placeholder="请选择培养层次" clearable>
          <el-option label="本科" value="本科" />
          <el-option label="专科" value="专科" />
          <el-option label="研究生" value="研究生" />
          <el-option label="博士" value="博士" />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option label="正常" :value="1" />
          <el-option label="停用" :value="0" />
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
          v-hasPermi="['teachingmanagement:major:add']"
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
          v-hasPermi="['teachingmanagement:major:edit']"
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
          v-hasPermi="['teachingmanagement:major:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['teachingmanagement:major:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="majorList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="专业ID" align="center" prop="majorId" width="80" />
      <el-table-column label="专业名称" align="center" prop="majorName" min-width="120" show-overflow-tooltip />
      <el-table-column label="专业编码" align="center" prop="majorCode" width="100" />
      <el-table-column label="关联院系ID" align="center" prop="deptId" width="100" />
      <el-table-column label="培养层次" align="center" prop="trainingLevel" width="80" />
      <el-table-column label="状态" align="center" prop="status" width="80">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status === 1" type="success">正常</el-tag>
          <el-tag v-else-if="scope.row.status === 0" type="danger">停用</el-tag>
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
            v-hasPermi="['teachingmanagement:major:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['teachingmanagement:major:remove']"
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

    <!-- 添加或修改专业管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="专业名称" prop="majorName">
          <el-input v-model="form.majorName" placeholder="请输入专业名称" />
        </el-form-item>
        <el-form-item label="专业编码" prop="majorCode">
          <el-input v-model="form.majorCode" placeholder="请输入专业编码" />
        </el-form-item>
        <el-form-item label="关联院系ID" prop="deptId">
          <el-input v-model="form.deptId" placeholder="请输入关联院系ID" type="number" />
        </el-form-item>
        <el-form-item label="培养层次" prop="trainingLevel">
          <el-select v-model="form.trainingLevel" placeholder="请选择培养层次" style="width: 100%">
            <el-option label="本科" value="本科" />
            <el-option label="专科" value="专科" />
            <el-option label="研究生" value="研究生" />
            <el-option label="博士" value="博士" />
          </el-select>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio :label="1">正常</el-radio>
            <el-radio :label="0">停用</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="更新用户" prop="updateUserId">
          <el-input v-model="form.updateUserId" placeholder="请输入更新用户ID" type="number" />
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
import { listMajor, getMajor, delMajor, addMajor, updateMajor } from "@/api/teachingmanagement/major"

export default {
  name: "Major",
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
      // 专业管理表格数据
      majorList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        majorName: null,
        majorCode: null,
        deptId: null,
        trainingLevel: null,
        status: null,
        updateUserId: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        majorName: [
          { required: true, message: "专业名称不能为空", trigger: "blur" },
          { min: 1, max: 100, message: "长度在 1 到 100 个字符", trigger: "blur" }
        ],
        majorCode: [
          { required: true, message: "专业编码不能为空", trigger: "blur" },
          { min: 1, max: 20, message: "长度在 1 到 20 个字符", trigger: "blur" }
        ],
        deptId: [
          { required: true, message: "关联院系ID不能为空", trigger: "blur" },
          { validator: validateNumber, trigger: "blur" }
        ],
        trainingLevel: [
          { required: true, message: "培养层次不能为空", trigger: "change" }
        ],
        status: [
          { required: true, message: "状态不能为空", trigger: "change" }
        ],
        updateUserId: [
          { required: true, message: "更新用户不能为空", trigger: "blur" },
          { validator: validateNumber, trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询专业管理列表 */
    getList() {
      this.loading = true
      listMajor(this.queryParams).then(response => {
        this.majorList = response.rows
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
        majorId: null,
        majorName: null,
        majorCode: null,
        deptId: null,
        trainingLevel: '本科', // 默认本科
        status: 1, // 默认正常状态
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
      this.ids = selection.map(item => item.majorId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加专业管理"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const majorId = row.majorId || this.ids[0]
      getMajor(majorId).then(response => {
        this.form = response.data
        // 确保数字字段类型正确
        if (this.form.status) this.form.status = Number(this.form.status)
        if (this.form.isDeleted) this.form.isDeleted = Number(this.form.isDeleted)
        if (this.form.deptId) this.form.deptId = Number(this.form.deptId)
        if (this.form.updateUserId) this.form.updateUserId = Number(this.form.updateUserId)

        this.open = true
        this.title = "修改专业管理"
      }).catch(error => {
        console.error("获取专业信息失败:", error)
        this.$modal.msgError("获取专业信息失败")
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          // 数据类型转换
          const submitData = {
            ...this.form,
            status: Number(this.form.status),
            isDeleted: Number(this.form.isDeleted || 0)
          }

          // 转换ID字段为数字
          if (this.form.deptId) submitData.deptId = Number(this.form.deptId)
          if (this.form.updateUserId) submitData.updateUserId = Number(this.form.updateUserId)

          if (submitData.majorId != null) {
            updateMajor(submitData).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            }).catch(error => {
              console.error("修改失败:", error)
              this.$modal.msgError("修改失败")
            })
          } else {
            addMajor(submitData).then(response => {
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
      const majorIds = row.majorId || this.ids
      this.$modal.confirm('是否确认删除专业管理编号为"' + majorIds + '"的数据项？').then(() => {
        return delMajor(majorIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('teachingmanagement/major/export', {
        ...this.queryParams
      }, `major_${new Date().getTime()}.xlsx`)
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
