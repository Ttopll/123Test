<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="试卷名称" prop="paperName">
        <el-input
          v-model="queryParams.paperName"
          placeholder="请输入试卷名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关联课程 ID" prop="courseId">
        <el-input
          v-model="queryParams.courseId"
          placeholder="请输入关联课程 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="试卷总分" prop="totalScore">
        <el-input
          v-model="queryParams.totalScore"
          placeholder="请输入试卷总分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="考试时长" prop="examTime">
        <el-input
          v-model="queryParams.examTime"
          placeholder="请输入考试时长"
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
      <el-form-item label="审核人 ID" prop="auditorId">
        <el-input
          v-model="queryParams.auditorId"
          placeholder="请输入审核人 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核时间" prop="auditTime">
        <el-date-picker clearable
          v-model="queryParams.auditTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择审核时间">
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
          v-hasPermi="['aipaper:aipaper_paper:add']"
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
          v-hasPermi="['aipaper:aipaper_paper:edit']"
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
          v-hasPermi="['aipaper:aipaper_paper:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['aipaper:aipaper_paper:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="aipaper_paperList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="试卷 ID" align="center" prop="paperId" />
      <el-table-column label="试卷名称" align="center" prop="paperName" />
      <el-table-column label="关联课程 ID" align="center" prop="courseId" />
      <el-table-column label="试卷总分" align="center" prop="totalScore" />
      <el-table-column label="考试时长" align="center" prop="examTime" />
      <el-table-column label="关联试题 ID" align="center" prop="questionIds" />
      <el-table-column label="创建人 ID" align="center" prop="createUserId" />
      <el-table-column label="审核状态" align="center" prop="auditStatus" />
      <el-table-column label="审核人 ID" align="center" prop="auditorId" />
      <el-table-column label="审核时间" align="center" prop="auditTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.auditTime, '{y}-{m}-{d}') }}</span>
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
            v-hasPermi="['aipaper:aipaper_paper:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['aipaper:aipaper_paper:remove']"
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

    <!-- 添加或修改试卷对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="试卷名称" prop="paperName">
          <el-input v-model="form.paperName" placeholder="请输入试卷名称" />
        </el-form-item>
        <el-form-item label="关联课程 ID" prop="courseId">
          <el-input v-model="form.courseId" placeholder="请输入关联课程 ID" />
        </el-form-item>
        <el-form-item label="试卷总分" prop="totalScore">
          <el-input v-model="form.totalScore" placeholder="请输入试卷总分" />
        </el-form-item>
        <el-form-item label="考试时长" prop="examTime">
          <el-input v-model="form.examTime" placeholder="请输入考试时长" />
        </el-form-item>
        <el-form-item label="关联试题 ID" prop="questionIds">
          <el-input v-model="form.questionIds" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="创建人 ID" prop="createUserId">
          <el-input v-model="form.createUserId" placeholder="请输入创建人 ID" />
        </el-form-item>
        <el-form-item label="审核人 ID" prop="auditorId">
          <el-input v-model="form.auditorId" placeholder="请输入审核人 ID" />
        </el-form-item>
        <el-form-item label="审核时间" prop="auditTime">
          <el-date-picker clearable
            v-model="form.auditTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择审核时间">
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
import { listAipaper_paper, getAipaper_paper, delAipaper_paper, addAipaper_paper, updateAipaper_paper } from "@/api/aipaper/aipaper_paper"

export default {
  name: "Aipaper_paper",
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
      // 试卷表格数据
      aipaper_paperList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        paperName: null,
        courseId: null,
        totalScore: null,
        examTime: null,
        questionIds: null,
        createUserId: null,
        auditStatus: null,
        auditorId: null,
        auditTime: null,
        status: null,
        updateUserId: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        paperName: [
          { required: true, message: "试卷名称不能为空", trigger: "blur" }
        ],
        courseId: [
          { required: true, message: "关联课程 ID不能为空", trigger: "blur" }
        ],
        totalScore: [
          { required: true, message: "试卷总分不能为空", trigger: "blur" }
        ],
        examTime: [
          { required: true, message: "考试时长不能为空", trigger: "blur" }
        ],
        questionIds: [
          { required: true, message: "关联试题 ID不能为空", trigger: "blur" }
        ],
        createUserId: [
          { required: true, message: "创建人 ID不能为空", trigger: "blur" }
        ],
        auditStatus: [
          { required: true, message: "审核状态不能为空", trigger: "change" }
        ],
        auditorId: [
          { required: true, message: "审核人 ID不能为空", trigger: "blur" }
        ],
        auditTime: [
          { required: true, message: "审核时间不能为空", trigger: "blur" }
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
    /** 查询试卷列表 */
    getList() {
      this.loading = true
      listAipaper_paper(this.queryParams).then(response => {
        this.aipaper_paperList = response.rows
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
        paperId: null,
        paperName: null,
        courseId: null,
        totalScore: null,
        examTime: null,
        questionIds: null,
        createUserId: null,
        auditStatus: null,
        auditorId: null,
        auditTime: null,
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
      this.ids = selection.map(item => item.paperId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加试卷"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const paperId = row.paperId || this.ids
      getAipaper_paper(paperId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改试卷"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.paperId != null) {
            updateAipaper_paper(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addAipaper_paper(this.form).then(response => {
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
      const paperIds = row.paperId || this.ids
      this.$modal.confirm('是否确认删除试卷编号为"' + paperIds + '"的数据项？').then(function() {
        return delAipaper_paper(paperIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('aipaper/aipaper_paper/export', {
        ...this.queryParams
      }, `aipaper_paper_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
