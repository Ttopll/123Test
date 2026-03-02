<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="关联课程 ID" prop="courseId">
        <el-input
          v-model="queryParams.courseId"
          placeholder="请输入关联课程 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="试题分值" prop="score">
        <el-input
          v-model="queryParams.score"
          placeholder="请输入试题分值"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="难度等级" prop="difficulty">
        <el-input
          v-model="queryParams.difficulty"
          placeholder="请输入难度等级"
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
          v-hasPermi="['aipaper:aipaper_question:add']"
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
          v-hasPermi="['aipaper:aipaper_question:edit']"
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
          v-hasPermi="['aipaper:aipaper_question:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['aipaper:aipaper_question:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="aipaper_questionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="试题 ID" align="center" prop="questionId" />
      <el-table-column label="关联课程 ID" align="center" prop="courseId" />
      <el-table-column label="试题类型" align="center" prop="questionType" />
      <el-table-column label="试题内容" align="center" prop="questionContent" />
      <el-table-column label="选项内容" align="center" prop="optionContent" />
      <el-table-column label="参考答案" align="center" prop="answer" />
      <el-table-column label="试题分值" align="center" prop="score" />
      <el-table-column label="难度等级" align="center" prop="difficulty" />
      <el-table-column label="创建人 ID" align="center" prop="createUserId" />
      <el-table-column label="更新用户" align="center" prop="updateUserId" />
      <el-table-column label="删除状态" align="center" prop="isDeleted" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['aipaper:aipaper_question:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['aipaper:aipaper_question:remove']"
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

    <!-- 添加或修改试题对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="关联课程 ID" prop="courseId">
          <el-input v-model="form.courseId" placeholder="请输入关联课程 ID" />
        </el-form-item>
        <el-form-item label="试题内容">
          <editor v-model="form.questionContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="选项内容">
          <editor v-model="form.optionContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="参考答案" prop="answer">
          <el-input v-model="form.answer" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="试题分值" prop="score">
          <el-input v-model="form.score" placeholder="请输入试题分值" />
        </el-form-item>
        <el-form-item label="难度等级" prop="difficulty">
          <el-input v-model="form.difficulty" placeholder="请输入难度等级" />
        </el-form-item>
        <el-form-item label="创建人 ID" prop="createUserId">
          <el-input v-model="form.createUserId" placeholder="请输入创建人 ID" />
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
import { listAipaper_question, getAipaper_question, delAipaper_question, addAipaper_question, updateAipaper_question } from "@/api/aipaper/aipaper_question"

export default {
  name: "Aipaper_question",
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
      // 试题表格数据
      aipaper_questionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        courseId: null,
        questionType: null,
        questionContent: null,
        optionContent: null,
        answer: null,
        score: null,
        difficulty: null,
        createUserId: null,
        updateUserId: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        courseId: [
          { required: true, message: "关联课程 ID不能为空", trigger: "blur" }
        ],
        questionType: [
          { required: true, message: "试题类型不能为空", trigger: "change" }
        ],
        questionContent: [
          { required: true, message: "试题内容不能为空", trigger: "blur" }
        ],
        answer: [
          { required: true, message: "参考答案不能为空", trigger: "blur" }
        ],
        score: [
          { required: true, message: "试题分值不能为空", trigger: "blur" }
        ],
        difficulty: [
          { required: true, message: "难度等级不能为空", trigger: "blur" }
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
          { required: true, message: "删除状态不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询试题列表 */
    getList() {
      this.loading = true
      listAipaper_question(this.queryParams).then(response => {
        this.aipaper_questionList = response.rows
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
        questionId: null,
        courseId: null,
        questionType: null,
        questionContent: null,
        optionContent: null,
        answer: null,
        score: null,
        difficulty: null,
        createUserId: null,
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
      this.ids = selection.map(item => item.questionId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加试题"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const questionId = row.questionId || this.ids
      getAipaper_question(questionId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改试题"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.questionId != null) {
            updateAipaper_question(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addAipaper_question(this.form).then(response => {
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
      const questionIds = row.questionId || this.ids
      this.$modal.confirm('是否确认删除试题编号为"' + questionIds + '"的数据项？').then(function() {
        return delAipaper_question(questionIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('aipaper/aipaper_question/export', {
        ...this.queryParams
      }, `aipaper_question_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
