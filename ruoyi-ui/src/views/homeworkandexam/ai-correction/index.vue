<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="关联作业提交ID" prop="submitId">
        <el-input
          v-model="queryParams.submitId"
          placeholder="请输入关联作业提交ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关联作业ID" prop="assignId">
        <el-input
          v-model="queryParams.assignId"
          placeholder="请输入关联作业ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学生ID" prop="studentId">
        <el-input
          v-model="queryParams.studentId"
          placeholder="请输入学生ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="授课教师ID" prop="teacherId">
        <el-input
          v-model="queryParams.teacherId"
          placeholder="请输入授课教师ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="AI自动评分" prop="aiScore">
        <el-input
          v-model="queryParams.aiScore"
          placeholder="请输入AI自动评分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="教师修正后得分" prop="manualScore">
        <el-input
          v-model="queryParams.manualScore"
          placeholder="请输入教师修正后得分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="批改相似度" prop="similarityRate">
        <el-input
          v-model="queryParams.similarityRate"
          placeholder="请输入批改相似度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="批改状态" prop="correctionStatus">
        <el-select v-model="queryParams.correctionStatus" placeholder="请选择批改状态" clearable>
          <el-option label="AI批改中" :value="1" />
          <el-option label="AI批改完成" :value="2" />
          <el-option label="教师修正中" :value="3" />
          <el-option label="教师修正完成" :value="4" />
          <el-option label="已完成" :value="5" />
        </el-select>
      </el-form-item>
      <el-form-item label="AI批改时间" prop="correctionTime">
        <el-date-picker clearable
                        v-model="queryParams.correctionTime"
                        type="datetime"
                        value-format="yyyy-MM-dd HH:mm:ss"
                        placeholder="请选择AI批改时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="人工修正时间" prop="manualTime">
        <el-date-picker clearable
                        v-model="queryParams.manualTime"
                        type="datetime"
                        value-format="yyyy-MM-dd HH:mm:ss"
                        placeholder="请选择人工修正时间">
        </el-date-picker>
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
          v-hasPermi="['homeworkandexam:ai-correction:add']"
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
          v-hasPermi="['homeworkandexam:ai-correction:edit']"
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
          v-hasPermi="['homeworkandexam:ai-correction:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['homeworkandexam:ai-correction:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="aiCorrectionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="AI批改记录ID" align="center" prop="correctionId" width="100" />
      <el-table-column label="关联作业提交ID" align="center" prop="submitId" width="100" />
      <el-table-column label="关联作业ID" align="center" prop="assignId" width="80" />
      <el-table-column label="学生ID" align="center" prop="studentId" width="100" />
      <el-table-column label="授课教师ID" align="center" prop="teacherId" width="100" />
      <el-table-column label="AI自动评分" align="center" prop="aiScore" width="80" />
      <el-table-column label="AI批改详情" align="center" prop="aiCorrectionContent" min-width="150" show-overflow-tooltip />
      <el-table-column label="教师修正后得分" align="center" prop="manualScore" width="100" />
      <el-table-column label="教师修正详情" align="center" prop="manualCorrectionContent" min-width="150" show-overflow-tooltip />
      <el-table-column label="批改相似度" align="center" prop="similarityRate" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.similarityRate }}%</span>
        </template>
      </el-table-column>
      <el-table-column label="批改状态" align="center" prop="correctionStatus" width="100">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.correctionStatus === 1" type="info">AI批改中</el-tag>
          <el-tag v-else-if="scope.row.correctionStatus === 2" type="primary">AI批改完成</el-tag>
          <el-tag v-else-if="scope.row.correctionStatus === 3" type="warning">教师修正中</el-tag>
          <el-tag v-else-if="scope.row.correctionStatus === 4" type="success">教师修正完成</el-tag>
          <el-tag v-else-if="scope.row.correctionStatus === 5" type="success">已完成</el-tag>
          <span v-else>{{ scope.row.correctionStatus }}</span>
        </template>
      </el-table-column>
      <el-table-column label="AI批改时间" align="center" prop="correctionTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.correctionTime, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="人工修正时间" align="center" prop="manualTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.manualTime, '{y}-{m}-{d} {h}:{i}') }}</span>
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
            v-hasPermi="['homeworkandexam:ai-correction:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['homeworkandexam:ai-correction:remove']"
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

    <!-- 添加或修改AI批改记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="关联作业提交ID" prop="submitId">
          <el-input v-model="form.submitId" placeholder="请输入关联作业提交ID" type="number" />
        </el-form-item>
        <el-form-item label="关联作业ID" prop="assignId">
          <el-input v-model="form.assignId" placeholder="请输入关联作业ID" type="number" />
        </el-form-item>
        <el-form-item label="学生ID" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学生ID" />
        </el-form-item>
        <el-form-item label="授课教师ID" prop="teacherId">
          <el-input v-model="form.teacherId" placeholder="请输入授课教师ID" />
        </el-form-item>
        <el-form-item label="AI自动评分" prop="aiScore">
          <el-input v-model="form.aiScore" placeholder="请输入AI自动评分(0-100)" type="number" min="0" max="100" />
        </el-form-item>
        <el-form-item label="AI批改详情" prop="aiCorrectionContent">
          <editor v-model="form.aiCorrectionContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="教师修正后得分" prop="manualScore">
          <el-input v-model="form.manualScore" placeholder="请输入教师修正后得分(0-100)" type="number" min="0" max="100" />
        </el-form-item>
        <el-form-item label="教师修正详情">
          <editor v-model="form.manualCorrectionContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="批改相似度" prop="similarityRate">
          <el-input v-model="form.similarityRate" placeholder="请输入批改相似度(0.00-100.00)" type="number" min="0" max="100" step="0.01">
            <template slot="append">%</template>
          </el-input>
        </el-form-item>
        <el-form-item label="批改状态" prop="correctionStatus">
          <el-radio-group v-model="form.correctionStatus">
            <el-radio :label="1">AI批改中</el-radio>
            <el-radio :label="2">AI批改完成</el-radio>
            <el-radio :label="3">教师修正中</el-radio>
            <el-radio :label="4">教师修正完成</el-radio>
            <el-radio :label="5">已完成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="AI批改时间" prop="correctionTime">
          <el-date-picker clearable
                          v-model="form.correctionTime"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="请选择AI批改时间"
                          style="width: 100%">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="人工修正时间" prop="manualTime">
          <el-date-picker clearable
                          v-model="form.manualTime"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="请选择人工修正时间"
                          style="width: 100%">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="更新用户" prop="updateUserId">
          <el-input v-model="form.updateUserId" placeholder="请输入更新用户ID" />
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
import { listAiCorrection, getAiCorrection, delAiCorrection, addAiCorrection, updateAiCorrection } from "@/api/homeworkandexam/ai-correction"

export default {
  name: "AiCorrection",
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

    // 验证整数类型
    const validateInteger = (rule, value, callback) => {
      if (value === '' || value === null || value === undefined) {
        callback(new Error('该字段不能为空'));
        return;
      }
      if (isNaN(Number(value)) || !Number.isInteger(Number(value))) {
        callback(new Error('请输入有效的整数'));
        return;
      }
      if (Number(value) < 0 || Number(value) > 100) {
        callback(new Error('数值必须在0-100之间'));
        return;
      }
      callback();
    };

    // 验证小数类型
    const validateDecimal = (rule, value, callback) => {
      if (value === '' || value === null || value === undefined) {
        callback(new Error('该字段不能为空'));
        return;
      }
      if (isNaN(Number(value))) {
        callback(new Error('请输入有效的数字'));
        return;
      }
      if (Number(value) < 0 || Number(value) > 100) {
        callback(new Error('数值必须在0.00-100.00之间'));
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
      // AI批改记录表格数据
      aiCorrectionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        submitId: null,
        assignId: null,
        studentId: null,
        teacherId: null,
        aiScore: null,
        aiCorrectionContent: null,
        manualScore: null,
        manualCorrectionContent: null,
        similarityRate: null,
        correctionStatus: null,
        correctionTime: null,
        manualTime: null,
        updateUserId: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        submitId: [
          { required: true, message: "关联作业提交ID不能为空", trigger: "blur" },
          { validator: validateNumber, trigger: "blur" }
        ],
        assignId: [
          { required: true, message: "关联作业ID不能为空", trigger: "blur" },
          { validator: validateNumber, trigger: "blur" }
        ],
        studentId: [
          { required: true, message: "学生ID不能为空", trigger: "blur" }
        ],
        teacherId: [
          { required: true, message: "授课教师ID不能为空", trigger: "blur" }
        ],
        aiScore: [
          { required: true, message: "AI自动评分不能为空", trigger: "blur" },
          { validator: validateInteger, trigger: "blur" }
        ],
        aiCorrectionContent: [
          { required: true, message: "AI批改详情不能为空", trigger: "blur" }
        ],
        similarityRate: [
          { required: true, message: "批改相似度不能为空", trigger: "blur" },
          { validator: validateDecimal, trigger: "blur" }
        ],
        correctionStatus: [
          { required: true, message: "批改状态不能为空", trigger: "change" }
        ],
        correctionTime: [
          { required: true, message: "AI批改时间不能为空", trigger: "change" }
        ],
        updateUserId: [
          { required: true, message: "更新用户不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询AI批改记录列表 */
    getList() {
      this.loading = true
      listAiCorrection(this.queryParams).then(response => {
        this.aiCorrectionList = response.rows
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
        correctionId: null,
        submitId: null,
        assignId: null,
        studentId: null,
        teacherId: null,
        aiScore: null,
        aiCorrectionContent: null,
        manualScore: null,
        manualCorrectionContent: null,
        similarityRate: null,
        correctionStatus: 1, // 默认AI批改中
        correctionTime: null,
        manualTime: null,
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
      this.ids = selection.map(item => item.correctionId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加AI批改记录"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const correctionId = row.correctionId || this.ids[0]
      getAiCorrection(correctionId).then(response => {
        this.form = response.data
        // 确保数字字段类型正确
        if (this.form.correctionStatus) this.form.correctionStatus = Number(this.form.correctionStatus)
        if (this.form.isDeleted) this.form.isDeleted = Number(this.form.isDeleted)
        if (this.form.submitId) this.form.submitId = Number(this.form.submitId)
        if (this.form.assignId) this.form.assignId = Number(this.form.assignId)
        if (this.form.aiScore) this.form.aiScore = Number(this.form.aiScore)
        if (this.form.manualScore) this.form.manualScore = Number(this.form.manualScore)
        if (this.form.similarityRate) this.form.similarityRate = Number(this.form.similarityRate)

        this.open = true
        this.title = "修改AI批改记录"
      }).catch(error => {
        console.error("获取AI批改信息失败:", error)
        this.$modal.msgError("获取AI批改信息失败")
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          // 数据类型转换
          const submitData = {
            ...this.form,
            correctionStatus: Number(this.form.correctionStatus),
            isDeleted: Number(this.form.isDeleted || 0)
          }

          // 转换ID字段为数字
          if (this.form.submitId) submitData.submitId = Number(this.form.submitId)
          if (this.form.assignId) submitData.assignId = Number(this.form.assignId)
          if (this.form.aiScore) submitData.aiScore = Number(this.form.aiScore)
          if (this.form.manualScore) submitData.manualScore = Number(this.form.manualScore)
          if (this.form.similarityRate) submitData.similarityRate = Number(this.form.similarityRate)

          if (submitData.correctionId != null) {
            updateAiCorrection(submitData).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            }).catch(error => {
              console.error("修改失败:", error)
              this.$modal.msgError("修改失败")
            })
          } else {
            addAiCorrection(submitData).then(response => {
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
      const correctionIds = row.correctionId || this.ids
      this.$modal.confirm('是否确认删除AI批改记录编号为"' + correctionIds + '"的数据项？').then(() => {
        return delAiCorrection(correctionIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('homeworkandexam/ai-correction/export', {
        ...this.queryParams
      }, `ai-correction_${new Date().getTime()}.xlsx`)
    },
    // 时间格式化方法
    parseTime(time, cFormat) {
      if (!time) return ''
      if (arguments.length === 0) {
        return null
      }
      const format = cFormat || '{y}-{m}-{d} {h}:{i}:{s}'
      let date
      if (typeof time === 'object') {
        date = time
      } else {
        if ((typeof time === 'string') && (/^[0-9]+$/.test(time))) {
          time = parseInt(time)
        }
        if ((typeof time === 'number') && (time.toString().length === 10)) {
          time = time * 1000
        }
        date = new Date(time)
      }
      const formatObj = {
        y: date.getFullYear(),
        m: date.getMonth() + 1,
        d: date.getDate(),
        h: date.getHours(),
        i: date.getMinutes(),
        s: date.getSeconds(),
        a: date.getDay()
      }
      const time_str = format.replace(/{([ymdhisa])+}/g, (result, key) => {
        const value = formatObj[key]
        if (key === 'a') { return ['日', '一', '二', '三', '四', '五', '六'][value] }
        return value.toString().padStart(2, '0')
      })
      return time_str
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
