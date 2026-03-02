<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="配置 ID" prop="configId">
        <el-input
          v-model="queryParams.configId"
          placeholder="请输入配置 ID"
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
      <el-form-item label="学生与课程绑定 ID" prop="scId">
        <el-input
          v-model="queryParams.scId"
          placeholder="请输入学生与课程绑定 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="教师 ID" prop="teacherId">
        <el-input
          v-model="queryParams.teacherId"
          placeholder="请输入教师 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="课程 ID" prop="courseId">
        <el-input
          v-model="queryParams.courseId"
          placeholder="请输入课程 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="综合评分" prop="comprehensiveScore">
        <el-input
          v-model="queryParams.comprehensiveScore"
          placeholder="请输入综合评分"
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
      <el-form-item label="审核人 ID" prop="auditUserId">
        <el-input
          v-model="queryParams.auditUserId"
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
      <el-form-item label="记录有效性" prop="isValid">
        <el-input
          v-model="queryParams.isValid"
          placeholder="请输入记录有效性"
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
          v-hasPermi="['evaluate:evaluate_record:add']"
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
          v-hasPermi="['evaluate:evaluate_record:edit']"
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
          v-hasPermi="['evaluate:evaluate_record:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['evaluate:evaluate_record:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="evaluate_recordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="评价 ID" align="center" prop="recordId" />
      <el-table-column label="配置 ID" align="center" prop="configId" />
      <el-table-column label="学生 ID" align="center" prop="studentId" />
      <el-table-column label="学生与课程绑定 ID" align="center" prop="scId" />
      <el-table-column label="教师 ID" align="center" prop="teacherId" />
      <el-table-column label="课程 ID" align="center" prop="courseId" />
      <el-table-column label="各维度评分" align="center" prop="dimensionScores" />
      <el-table-column label="综合评分" align="center" prop="comprehensiveScore" />
      <el-table-column label="文字评价" align="center" prop="evaluationContent" />
      <el-table-column label="评价时间" align="center" prop="evaluationTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.evaluationTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核状态" align="center" prop="auditStatus" />
      <el-table-column label="审核人 ID" align="center" prop="auditUserId" />
      <el-table-column label="审核时间" align="center" prop="auditTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.auditTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核备注" align="center" prop="auditRemark" />
      <el-table-column label="记录有效性" align="center" prop="isValid" />
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
            v-hasPermi="['evaluate:evaluate_record:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['evaluate:evaluate_record:remove']"
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

    <!-- 添加或修改教师评价记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="配置 ID" prop="configId">
          <el-input v-model="form.configId" placeholder="请输入配置 ID" />
        </el-form-item>
        <el-form-item label="学生 ID" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学生 ID" />
        </el-form-item>
        <el-form-item label="学生与课程绑定 ID" prop="scId">
          <el-input v-model="form.scId" placeholder="请输入学生与课程绑定 ID" />
        </el-form-item>
        <el-form-item label="教师 ID" prop="teacherId">
          <el-input v-model="form.teacherId" placeholder="请输入教师 ID" />
        </el-form-item>
        <el-form-item label="课程 ID" prop="courseId">
          <el-input v-model="form.courseId" placeholder="请输入课程 ID" />
        </el-form-item>
        <el-form-item label="各维度评分" prop="dimensionScores">
          <el-input v-model="form.dimensionScores" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="综合评分" prop="comprehensiveScore">
          <el-input v-model="form.comprehensiveScore" placeholder="请输入综合评分" />
        </el-form-item>
        <el-form-item label="文字评价">
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
        <el-form-item label="审核人 ID" prop="auditUserId">
          <el-input v-model="form.auditUserId" placeholder="请输入审核人 ID" />
        </el-form-item>
        <el-form-item label="审核时间" prop="auditTime">
          <el-date-picker clearable
            v-model="form.auditTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择审核时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="审核备注" prop="auditRemark">
          <el-input v-model="form.auditRemark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="记录有效性" prop="isValid">
          <el-input v-model="form.isValid" placeholder="请输入记录有效性" />
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
import { listEvaluate_record, getEvaluate_record, delEvaluate_record, addEvaluate_record, updateEvaluate_record } from "@/api/evaluate/evaluate_record"

export default {
  name: "Evaluate_record",
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
      // 教师评价记录表格数据
      evaluate_recordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        configId: null,
        studentId: null,
        scId: null,
        teacherId: null,
        courseId: null,
        dimensionScores: null,
        comprehensiveScore: null,
        evaluationContent: null,
        evaluationTime: null,
        auditStatus: null,
        auditUserId: null,
        auditTime: null,
        auditRemark: null,
        isValid: null,
        updateUserId: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        configId: [
          { required: true, message: "配置 ID不能为空", trigger: "blur" }
        ],
        studentId: [
          { required: true, message: "学生 ID不能为空", trigger: "blur" }
        ],
        scId: [
          { required: true, message: "学生与课程绑定 ID不能为空", trigger: "blur" }
        ],
        teacherId: [
          { required: true, message: "教师 ID不能为空", trigger: "blur" }
        ],
        courseId: [
          { required: true, message: "课程 ID不能为空", trigger: "blur" }
        ],
        dimensionScores: [
          { required: true, message: "各维度评分不能为空", trigger: "blur" }
        ],
        comprehensiveScore: [
          { required: true, message: "综合评分不能为空", trigger: "blur" }
        ],
        evaluationTime: [
          { required: true, message: "评价时间不能为空", trigger: "blur" }
        ],
        auditStatus: [
          { required: true, message: "审核状态不能为空", trigger: "change" }
        ],
        isValid: [
          { required: true, message: "记录有效性不能为空", trigger: "blur" }
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
    /** 查询教师评价记录列表 */
    getList() {
      this.loading = true
      listEvaluate_record(this.queryParams).then(response => {
        this.evaluate_recordList = response.rows
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
        recordId: null,
        configId: null,
        studentId: null,
        scId: null,
        teacherId: null,
        courseId: null,
        dimensionScores: null,
        comprehensiveScore: null,
        evaluationContent: null,
        evaluationTime: null,
        auditStatus: null,
        auditUserId: null,
        auditTime: null,
        auditRemark: null,
        isValid: null,
        remark: null,
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
      this.ids = selection.map(item => item.recordId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加教师评价记录"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const recordId = row.recordId || this.ids
      getEvaluate_record(recordId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改教师评价记录"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.recordId != null) {
            updateEvaluate_record(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addEvaluate_record(this.form).then(response => {
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
      const recordIds = row.recordId || this.ids
      this.$modal.confirm('是否确认删除教师评价记录编号为"' + recordIds + '"的数据项？').then(function() {
        return delEvaluate_record(recordIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('evaluate/evaluate_record/export', {
        ...this.queryParams
      }, `evaluate_record_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
