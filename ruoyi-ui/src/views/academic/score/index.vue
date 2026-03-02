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
      <el-form-item label="课程 ID" prop="courseId">
        <el-input
          v-model="queryParams.courseId"
          placeholder="请输入课程 ID"
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
      <el-form-item label="学期" prop="semester">
        <el-input
          v-model="queryParams.semester"
          placeholder="请输入学期"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="考试类型" prop="examType">
        <el-select v-model="queryParams.examType" placeholder="请选择考试类型" clearable>
          <el-option label="期末考试" :value="1" />
          <el-option label="期中考试" :value="2" />
          <el-option label="平时测验" :value="3" />
          <el-option label="作业" :value="4" />
          <el-option label="实验" :value="5" />
        </el-select>
      </el-form-item>
      <el-form-item label="分数" prop="score">
        <el-input
          v-model="queryParams.score"
          placeholder="请输入分数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="成绩等级" prop="scoreLevel">
        <el-input
          v-model="queryParams.scoreLevel"
          placeholder="请输入成绩等级"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否及格" prop="isPass">
        <el-select v-model="queryParams.isPass" placeholder="请选择" clearable>
          <el-option label="是" :value="1" />
          <el-option label="否" :value="0" />
        </el-select>
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
        <el-select v-model="queryParams.isDeleted" placeholder="请选择" clearable>
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
          v-hasPermi="['academic:score:add']"
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
          v-hasPermi="['academic:score:edit']"
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
          v-hasPermi="['academic:score:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['academic:score:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="scoreList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="成绩 ID" align="center" prop="scoreId" />
      <el-table-column label="学生 ID" align="center" prop="studentId" />
      <el-table-column label="课程 ID" align="center" prop="courseId" />
      <el-table-column label="教师 ID" align="center" prop="teacherId" />
      <el-table-column label="学期" align="center" prop="semester" />
      <el-table-column label="考试类型" align="center" prop="examType">
        <template slot-scope="scope">
          <span v-if="scope.row.examType === 1">期末考试</span>
          <span v-else-if="scope.row.examType === 2">期中考试</span>
          <span v-else-if="scope.row.examType === 3">平时测验</span>
          <span v-else-if="scope.row.examType === 4">作业</span>
          <span v-else-if="scope.row.examType === 5">实验</span>
          <span v-else>{{ scope.row.examType }}</span>
        </template>
      </el-table-column>
      <el-table-column label="分数" align="center" prop="score" />
      <el-table-column label="成绩等级" align="center" prop="scoreLevel" />
      <el-table-column label="是否及格" align="center" prop="isPass">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.isPass === 1" type="success">是</el-tag>
          <el-tag v-else-if="scope.row.isPass === 0" type="danger">否</el-tag>
          <span v-else>{{ scope.row.isPass }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="更新用户" align="center" prop="updateUserId" />
      <el-table-column label="删除状态" align="center" prop="isDeleted">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.isDeleted === 0" type="success">正常</el-tag>
          <el-tag v-else-if="scope.row.isDeleted === 1" type="info">删除</el-tag>
          <span v-else>{{ scope.row.isDeleted }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['academic:score:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['academic:score:remove']"
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

    <!-- 添加或修改成绩管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学生 ID" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学生 ID" />
        </el-form-item>
        <el-form-item label="课程 ID" prop="courseId">
          <el-input v-model="form.courseId" placeholder="请输入课程 ID" />
        </el-form-item>
        <el-form-item label="教师 ID" prop="teacherId">
          <el-input v-model="form.teacherId" placeholder="请输入教师 ID" />
        </el-form-item>
        <el-form-item label="学期" prop="semester">
          <el-input v-model="form.semester" placeholder="请输入学期" />
        </el-form-item>
        <el-form-item label="考试类型" prop="examType">
          <el-select v-model="form.examType" placeholder="请选择考试类型">
            <el-option label="期末考试" :value="1" />
            <el-option label="期中考试" :value="2" />
            <el-option label="平时测验" :value="3" />
            <el-option label="作业" :value="4" />
            <el-option label="实验" :value="5" />
          </el-select>
        </el-form-item>
        <el-form-item label="分数" prop="score">
          <el-input v-model="form.score" placeholder="请输入分数" type="number" min="0" max="100" step="0.01" />
        </el-form-item>
        <el-form-item label="成绩等级" prop="scoreLevel">
          <el-input v-model="form.scoreLevel" placeholder="请输入成绩等级" />
        </el-form-item>
        <el-form-item label="是否及格" prop="isPass">
          <el-radio-group v-model="form.isPass">
            <el-radio :label="1">是</el-radio>
            <el-radio :label="0">否</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="更新用户" prop="updateUserId">
          <el-input v-model="form.updateUserId" placeholder="请输入更新用户" />
        </el-form-item>
        <el-form-item label="删除状态" prop="isDeleted">
          <el-radio-group v-model="form.isDeleted">
            <el-radio :label="0">正常</el-radio>
            <el-radio :label="1">删除</el-radio>
          </el-radio-group>
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
import { listScore, getScore, delScore, addScore, updateScore } from "@/api/academic/score"

export default {
  name: "Score",
  data() {
    // 分数验证函数
    const validateScore = (rule, value, callback) => {
      if (value === '' || value === null || value === undefined) {
        callback(new Error('分数不能为空'));
        return;
      }

      // 转换为数字进行验证
      const numValue = Number(value);
      if (isNaN(numValue)) {
        callback(new Error('分数必须为有效数字'));
        return;
      }

      if (numValue < 0 || numValue > 100) {
        callback(new Error('分数必须在0-100之间'));
        return;
      }

      // 检查小数位数（如果有）
      const strValue = String(value);
      const decimalIndex = strValue.indexOf('.');
      if (decimalIndex !== -1) {
        const decimalPart = strValue.substring(decimalIndex + 1);
        if (decimalPart.length > 2) {
          callback(new Error('分数最多支持两位小数'));
          return;
        }
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
      // 成绩管理表格数据
      scoreList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        studentId: null,
        courseId: null,
        teacherId: null,
        semester: null,
        examType: null,
        score: null,
        scoreLevel: null,
        isPass: null,
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
        courseId: [
          { required: true, message: "课程 ID不能为空", trigger: "blur" }
        ],
        teacherId: [
          { required: true, message: "教师 ID不能为空", trigger: "blur" }
        ],
        semester: [
          { required: true, message: "学期不能为空", trigger: "blur" }
        ],
        examType: [
          { required: true, message: "考试类型不能为空", trigger: "change" }
        ],
        score: [
          { required: true, message: "分数不能为空", trigger: "blur" },
          { validator: validateScore, trigger: 'blur' }
        ],
        isPass: [
          { required: true, message: "是否及格不能为空", trigger: "change" }
        ],
        updateUserId: [
          { required: true, message: "更新用户不能为空", trigger: "blur" }
        ],
        isDeleted: [
          { required: true, message: "删除状态不能为空", trigger: "change" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询成绩管理列表 */
    getList() {
      this.loading = true
      listScore(this.queryParams).then(response => {
        this.scoreList = response.rows
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
        scoreId: null,
        studentId: null,
        courseId: null,
        teacherId: null,
        semester: null,
        examType: 1, // 设置为数字类型，默认期末考试
        score: null,
        scoreLevel: null,
        isPass: 1, // 默认及格
        remark: null,
        createTime: null,
        updateTime: null,
        updateUserId: null,
        isDeleted: 0 // 默认正常状态
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
      this.ids = selection.map(item => item.scoreId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加成绩管理"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const scoreId = row.scoreId || this.ids
      getScore(scoreId).then(response => {
        this.form = response.data
        // 确保数字类型
        if (this.form.examType) {
          this.form.examType = Number(this.form.examType)
        }
        if (this.form.score) {
          this.form.score = Number(this.form.score)
        }
        this.open = true
        this.title = "修改成绩管理"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          // 确保所有数字字段都是正确的类型
          if (this.form.examType) {
            this.form.examType = Number(this.form.examType)
          }
          if (this.form.score !== null && this.form.score !== undefined && this.form.score !== '') {
            this.form.score = parseFloat(this.form.score)
          }
          if (this.form.isPass) {
            this.form.isPass = Number(this.form.isPass)
          }
          if (this.form.isDeleted) {
            this.form.isDeleted = Number(this.form.isDeleted)
          }
          if (this.form.studentId) {
            this.form.studentId = Number(this.form.studentId)
          }
          if (this.form.courseId) {
            this.form.courseId = Number(this.form.courseId)
          }
          if (this.form.teacherId) {
            this.form.teacherId = Number(this.form.teacherId)
          }
          if (this.form.updateUserId) {
            this.form.updateUserId = Number(this.form.updateUserId)
          }

          if (this.form.scoreId != null) {
            updateScore(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            }).catch((error) => {
              console.error("修改失败:", error)
              this.$modal.msgError("修改失败")
            })
          } else {
            addScore(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            }).catch((error) => {
              console.error("新增失败:", error)
              this.$modal.msgError("新增失败")
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const scoreIds = row.scoreId || this.ids
      this.$modal.confirm('是否确认删除成绩管理编号为"' + scoreIds + '"的数据项？').then(() => {
        return delScore(scoreIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('academic/score/export', {
        ...this.queryParams
      }, `score_${new Date().getTime()}.xlsx`)
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
