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
      <el-form-item label="记录标题" prop="recordTitle">
        <el-input
            v-model="queryParams.recordTitle"
            placeholder="请输入记录标题"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="记录类型" prop="recordType">
        <el-select v-model="queryParams.recordType" placeholder="请选择记录类型" clearable>
          <el-option
              v-for="item in recordTypeOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="相关时间" prop="relatedTime">
        <el-date-picker clearable
                        v-model="queryParams.relatedTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择相关时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="附件 URL" prop="attachUrl">
        <el-input
            v-model="queryParams.attachUrl"
            placeholder="请输入附件 URL"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="成长趋势标签" prop="growthTrend">
        <el-input
            v-model="queryParams.growthTrend"
            placeholder="请输入成长趋势标签"
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
            v-hasPermi="['academic:growth-record:add']"
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
            v-hasPermi="['academic:growth-record:edit']"
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
            v-hasPermi="['academic:growth-record:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['academic:growth-record:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="growthRecordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="记录 ID" align="center" prop="recordId" />
      <el-table-column label="学生 ID" align="center" prop="studentId" />
      <el-table-column label="记录类型" align="center" prop="recordType">
        <template slot-scope="scope">
          <span>{{ getRecordTypeLabel(scope.row.recordType) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="记录标题" align="center" prop="recordTitle" />
      <el-table-column label="记录详情" align="center" prop="recordContent" />
      <el-table-column label="相关时间" align="center" prop="relatedTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.relatedTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="附件 URL" align="center" prop="attachUrl" />
      <el-table-column label="成长趋势标签" align="center" prop="growthTrend" />
      <el-table-column label="更新用户" align="center" prop="updateUserId" />
      <el-table-column label="删除状态" align="center" prop="isDeleted" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['academic:growth-record:edit']"
          >修改</el-button>
          <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['academic:growth-record:remove']"
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

    <!-- 添加或修改学生成长记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学生 ID" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学生 ID" />
        </el-form-item>
        <el-form-item label="记录类型" prop="recordType">
          <el-select v-model="form.recordType" placeholder="请选择记录类型">
            <el-option
                v-for="item in recordTypeOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="记录标题" prop="recordTitle">
          <el-input v-model="form.recordTitle" placeholder="请输入记录标题" />
        </el-form-item>
        <el-form-item label="记录详情">
          <editor v-model="form.recordContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="相关时间" prop="relatedTime">
          <el-date-picker clearable
                          v-model="form.relatedTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择相关时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="附件 URL" prop="attachUrl">
          <el-input v-model="form.attachUrl" placeholder="请输入附件 URL" />
        </el-form-item>
        <el-form-item label="成长趋势标签" prop="growthTrend">
          <el-input v-model="form.growthTrend" placeholder="请输入成长趋势标签" />
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
import { listGrowthRecord, getGrowthRecord, delGrowthRecord, addGrowthRecord, updateGrowthRecord } from "@/api/academic/growth-record"

export default {
  name: "GrowthRecord",
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
      // 学生成长记录表格数据
      growthRecordList: [],
      // 记录类型选项
      recordTypeOptions: [
        { value: 1, label: '学业成绩' },
        { value: 2, label: '行为表现' },
        { value: 3, label: '课外活动' },
        { value: 4, label: '荣誉奖励' },
        { value: 5, label: '其他记录' }
      ],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        studentId: null,
        recordType: null,
        recordTitle: null,
        recordContent: null,
        relatedTime: null,
        attachUrl: null,
        growthTrend: null,
        updateUserId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        studentId: [
          { required: true, message: "学生 ID不能为空", trigger: "blur" }
        ],
        recordType: [
          { required: true, message: "记录类型不能为空", trigger: "change" }
        ],
        recordTitle: [
          { required: true, message: "记录标题不能为空", trigger: "blur" }
        ],
        relatedTime: [
          { required: true, message: "相关时间不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询学生成长记录列表 */
    getList() {
      this.loading = true
      listGrowthRecord(this.queryParams).then(response => {
        this.growthRecordList = response.rows
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
        studentId: null,
        recordType: null,
        recordTitle: null,
        recordContent: null,
        relatedTime: null,
        attachUrl: null,
        growthTrend: null
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
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加学生成长记录"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const recordId = row.recordId || this.ids
      getGrowthRecord(recordId).then(response => {
        this.form = response.data
        // 确保recordType是数字类型
        if (this.form.recordType) {
          this.form.recordType = Number(this.form.recordType)
        }
        this.open = true
        this.title = "修改学生成长记录"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          // 确保数据类型正确
          const formData = {
            ...this.form,
            recordType: this.form.recordType ? Number(this.form.recordType) : null,
            studentId: this.form.studentId ? Number(this.form.studentId) : null
          }

          if (formData.recordId != null) {
            updateGrowthRecord(formData).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addGrowthRecord(formData).then(response => {
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
      this.$modal.confirm('是否确认删除学生成长记录编号为"' + recordIds + '"的数据项？').then(function() {
        return delGrowthRecord(recordIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('academic/growth-record/export', {
        ...this.queryParams
      }, `growth-record_${new Date().getTime()}.xlsx`)
    },
    /** 获取记录类型标签 */
    getRecordTypeLabel(recordType) {
      const item = this.recordTypeOptions.find(option => option.value === recordType)
      return item ? item.label : recordType
    }
  }
}
</script>
