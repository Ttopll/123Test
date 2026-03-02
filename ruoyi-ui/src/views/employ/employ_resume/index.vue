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
      <el-form-item label="简历标题" prop="resumeTitle">
        <el-input
          v-model="queryParams.resumeTitle"
          placeholder="请输入简历标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="简历文件 URL" prop="resumeFileUrl">
        <el-input
          v-model="queryParams.resumeFileUrl"
          placeholder="请输入简历文件 URL"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文件大小" prop="fileSize">
        <el-input
          v-model="queryParams.fileSize"
          placeholder="请输入文件大小"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否默认简历" prop="isDefault">
        <el-input
          v-model="queryParams.isDefault"
          placeholder="请输入是否默认简历"
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
          v-hasPermi="['employ:employ_resume:add']"
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
          v-hasPermi="['employ:employ_resume:edit']"
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
          v-hasPermi="['employ:employ_resume:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['employ:employ_resume:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="employ_resumeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="简历 ID" align="center" prop="resumeId" />
      <el-table-column label="学生 ID" align="center" prop="studentId" />
      <el-table-column label="简历标题" align="center" prop="resumeTitle" />
      <el-table-column label="简历类型" align="center" prop="resumeType" />
      <el-table-column label="教育背景" align="center" prop="educationBg" />
      <el-table-column label="工作 / 实习经历" align="center" prop="workExp" />
      <el-table-column label="项目经历" align="center" prop="projectExp" />
      <el-table-column label="技能特长" align="center" prop="skill" />
      <el-table-column label="荣誉奖项" align="center" prop="honor" />
      <el-table-column label="简历文件 URL" align="center" prop="resumeFileUrl" />
      <el-table-column label="文件大小" align="center" prop="fileSize" />
      <el-table-column label="是否默认简历" align="center" prop="isDefault" />
      <el-table-column label="更新用户" align="center" prop="updateUserId" />
      <el-table-column label="删除状态" align="center" prop="isDeleted" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['employ:employ_resume:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['employ:employ_resume:remove']"
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

    <!-- 添加或修改学生简历管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学生 ID" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学生 ID" />
        </el-form-item>
        <el-form-item label="简历标题" prop="resumeTitle">
          <el-input v-model="form.resumeTitle" placeholder="请输入简历标题" />
        </el-form-item>
        <el-form-item label="教育背景" prop="educationBg">
          <el-input v-model="form.educationBg" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="工作 / 实习经历" prop="workExp">
          <el-input v-model="form.workExp" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="项目经历" prop="projectExp">
          <el-input v-model="form.projectExp" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="技能特长" prop="skill">
          <el-input v-model="form.skill" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="荣誉奖项" prop="honor">
          <el-input v-model="form.honor" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="简历文件 URL" prop="resumeFileUrl">
          <el-input v-model="form.resumeFileUrl" placeholder="请输入简历文件 URL" />
        </el-form-item>
        <el-form-item label="文件大小" prop="fileSize">
          <el-input v-model="form.fileSize" placeholder="请输入文件大小" />
        </el-form-item>
        <el-form-item label="是否默认简历" prop="isDefault">
          <el-input v-model="form.isDefault" placeholder="请输入是否默认简历" />
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
import { listEmploy_resume, getEmploy_resume, delEmploy_resume, addEmploy_resume, updateEmploy_resume } from "@/api/employ/employ_resume"

export default {
  name: "Employ_resume",
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
      // 学生简历管理表格数据
      employ_resumeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        studentId: null,
        resumeTitle: null,
        resumeType: null,
        educationBg: null,
        workExp: null,
        projectExp: null,
        skill: null,
        honor: null,
        resumeFileUrl: null,
        fileSize: null,
        isDefault: null,
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
        resumeTitle: [
          { required: true, message: "简历标题不能为空", trigger: "blur" }
        ],
        resumeType: [
          { required: true, message: "简历类型不能为空", trigger: "change" }
        ],
        educationBg: [
          { required: true, message: "教育背景不能为空", trigger: "blur" }
        ],
        skill: [
          { required: true, message: "技能特长不能为空", trigger: "blur" }
        ],
        resumeFileUrl: [
          { required: true, message: "简历文件 URL不能为空", trigger: "blur" }
        ],
        fileSize: [
          { required: true, message: "文件大小不能为空", trigger: "blur" }
        ],
        isDefault: [
          { required: true, message: "是否默认简历不能为空", trigger: "blur" }
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
    /** 查询学生简历管理列表 */
    getList() {
      this.loading = true
      listEmploy_resume(this.queryParams).then(response => {
        this.employ_resumeList = response.rows
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
        resumeId: null,
        studentId: null,
        resumeTitle: null,
        resumeType: null,
        educationBg: null,
        workExp: null,
        projectExp: null,
        skill: null,
        honor: null,
        resumeFileUrl: null,
        fileSize: null,
        isDefault: null,
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
      this.ids = selection.map(item => item.resumeId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加学生简历管理"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const resumeId = row.resumeId || this.ids
      getEmploy_resume(resumeId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改学生简历管理"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.resumeId != null) {
            updateEmploy_resume(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addEmploy_resume(this.form).then(response => {
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
      const resumeIds = row.resumeId || this.ids
      this.$modal.confirm('是否确认删除学生简历管理编号为"' + resumeIds + '"的数据项？').then(function() {
        return delEmploy_resume(resumeIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('employ/employ_resume/export', {
        ...this.queryParams
      }, `employ_resume_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
