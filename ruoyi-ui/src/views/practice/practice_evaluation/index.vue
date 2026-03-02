<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="活动 ID" prop="activityId">
        <el-input
          v-model="queryParams.activityId"
          placeholder="请输入活动 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="报名 ID" prop="signupId">
        <el-input
          v-model="queryParams.signupId"
          placeholder="请输入报名 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学生 ID" prop="studentId">
        <el-input
          v-model="queryParams.studentId"
          placeholder="请输入学生 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学生姓名" prop="studentName">
        <el-input
          v-model="queryParams.studentName"
          placeholder="请输入学生姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="评分" prop="score">
        <el-input
          v-model="queryParams.score"
          placeholder="请输入评分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="评价时间" prop="evaluationTime">
        <el-date-picker clearable
          v-model="queryParams.evaluationTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择评价时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="评价人 ID" prop="createUserId">
        <el-input
          v-model="queryParams.createUserId"
          placeholder="请输入评价人 ID"
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
      <el-form-item label="回复时间" prop="replyTime">
        <el-date-picker clearable
          v-model="queryParams.replyTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择回复时间">
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
          v-hasPermi="['practice:practice_evaluation:add']"
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
          v-hasPermi="['practice:practice_evaluation:edit']"
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
          v-hasPermi="['practice:practice_evaluation:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['practice:practice_evaluation:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="practice_evaluationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="评价 ID" align="center" prop="evaluationId" />
      <el-table-column label="活动 ID" align="center" prop="activityId" />
      <el-table-column label="报名 ID" align="center" prop="signupId" />
      <el-table-column label="学生 ID" align="center" prop="studentId" />
      <el-table-column label="学生姓名" align="center" prop="studentName" />
      <el-table-column label="实践结果" align="center" prop="practiveResult" />
      <el-table-column label="评价类型" align="center" prop="evaluationType" />
      <el-table-column label="评分" align="center" prop="score" />
      <el-table-column label="评价内容" align="center" prop="evaluationContent" />
      <el-table-column label="评价时间" align="center" prop="evaluationTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.evaluationTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="评价人 ID" align="center" prop="createUserId" />
      <el-table-column label="是否匿名" align="center" prop="isAnonymous" />
      <el-table-column label="回复内容" align="center" prop="replyContent" />
      <el-table-column label="回复时间" align="center" prop="replyTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.replyTime, '{y}-{m}-{d}') }}</span>
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
            v-hasPermi="['practice:practice_evaluation:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['practice:practice_evaluation:remove']"
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

    <!-- 添加或修改实践活动评价对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="活动 ID" prop="activityId">
          <el-input v-model="form.activityId" placeholder="请输入活动 ID" />
        </el-form-item>
        <el-form-item label="报名 ID" prop="signupId">
          <el-input v-model="form.signupId" placeholder="请输入报名 ID" />
        </el-form-item>
        <el-form-item label="学生 ID" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学生 ID" />
        </el-form-item>
        <el-form-item label="学生姓名" prop="studentName">
          <el-input v-model="form.studentName" placeholder="请输入学生姓名" />
        </el-form-item>
        <el-form-item label="实践结果" prop="practiveResult">
          <el-input v-model="form.practiveResult" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="评分" prop="score">
          <el-input v-model="form.score" placeholder="请输入评分" />
        </el-form-item>
        <el-form-item label="评价内容">
          <editor v-model="form.evaluationContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="评价时间" prop="evaluationTime">
          <el-date-picker clearable
            v-model="form.evaluationTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择评价时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="评价人 ID" prop="createUserId">
          <el-input v-model="form.createUserId" placeholder="请输入评价人 ID" />
        </el-form-item>
        <el-form-item label="是否匿名" prop="isAnonymous">
          <el-input v-model="form.isAnonymous" placeholder="请输入是否匿名" />
        </el-form-item>
        <el-form-item label="回复内容">
          <editor v-model="form.replyContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="回复时间" prop="replyTime">
          <el-date-picker clearable
            v-model="form.replyTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择回复时间">
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
import { listPractice_evaluation, getPractice_evaluation, delPractice_evaluation, addPractice_evaluation, updatePractice_evaluation } from "@/api/practice/practice_evaluation"

export default {
  name: "Practice_evaluation",
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
      // 实践活动评价表格数据
      practice_evaluationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        activityId: null,
        signupId: null,
        studentId: null,
        studentName: null,
        practiveResult: null,
        evaluationType: null,
        score: null,
        evaluationContent: null,
        evaluationTime: null,
        createUserId: null,
        isAnonymous: null,
        replyContent: null,
        replyTime: null,
        updateUserId: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        activityId: [
          { required: true, message: "活动 ID不能为空", trigger: "blur" }
        ],
        signupId: [
          { required: true, message: "报名 ID不能为空", trigger: "blur" }
        ],
        studentId: [
          { required: true, message: "学生 ID不能为空", trigger: "blur" }
        ],
        studentName: [
          { required: true, message: "学生姓名不能为空", trigger: "blur" }
        ],
        practiveResult: [
          { required: true, message: "实践结果不能为空", trigger: "blur" }
        ],
        evaluationType: [
          { required: true, message: "评价类型不能为空", trigger: "change" }
        ],
        score: [
          { required: true, message: "评分不能为空", trigger: "blur" }
        ],
        evaluationContent: [
          { required: true, message: "评价内容不能为空", trigger: "blur" }
        ],
        evaluationTime: [
          { required: true, message: "评价时间不能为空", trigger: "blur" }
        ],
        createUserId: [
          { required: true, message: "评价人 ID不能为空", trigger: "blur" }
        ],
        isAnonymous: [
          { required: true, message: "是否匿名不能为空", trigger: "blur" }
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
    /** 查询实践活动评价列表 */
    getList() {
      this.loading = true
      listPractice_evaluation(this.queryParams).then(response => {
        this.practice_evaluationList = response.rows
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
        evaluationId: null,
        activityId: null,
        signupId: null,
        studentId: null,
        studentName: null,
        practiveResult: null,
        evaluationType: null,
        score: null,
        evaluationContent: null,
        evaluationTime: null,
        createUserId: null,
        isAnonymous: null,
        replyContent: null,
        replyTime: null,
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
      this.ids = selection.map(item => item.evaluationId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加实践活动评价"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const evaluationId = row.evaluationId || this.ids
      getPractice_evaluation(evaluationId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改实践活动评价"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.evaluationId != null) {
            updatePractice_evaluation(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addPractice_evaluation(this.form).then(response => {
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
      const evaluationIds = row.evaluationId || this.ids
      this.$modal.confirm('是否确认删除实践活动评价编号为"' + evaluationIds + '"的数据项？').then(function() {
        return delPractice_evaluation(evaluationIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('practice/practice_evaluation/export', {
        ...this.queryParams
      }, `practice_evaluation_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
