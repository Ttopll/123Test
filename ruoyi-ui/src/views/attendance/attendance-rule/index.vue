<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="规则名称" prop="ruleName">
        <el-input
          v-model="queryParams.ruleName"
          placeholder="请输入规则名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="迟到判定分钟数" prop="lateMinute">
        <el-input
          v-model="queryParams.lateMinute"
          placeholder="请输入迟到判定分钟数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="缺勤判定分钟数" prop="absentCondition">
        <el-input
          v-model="queryParams.absentCondition"
          placeholder="请输入缺勤判定分钟数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="早退判定分钟数" prop="earlyLeaveMinute">
        <el-input
          v-model="queryParams.earlyLeaveMinute"
          placeholder="请输入早退判定分钟数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="定位签到范围" prop="signRange">
        <el-input
          v-model="queryParams.signRange"
          placeholder="请输入定位签到范围"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="考勤预警阈值" prop="warningThreshold">
        <el-input
          v-model="queryParams.warningThreshold"
          placeholder="请输入考勤预警阈值"
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
          v-hasPermi="['attendance:attendance-rule:add']"
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
          v-hasPermi="['attendance:attendance-rule:edit']"
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
          v-hasPermi="['attendance:attendance-rule:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['attendance:attendance-rule:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="attendanceRuleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="规则 ID" align="center" prop="ruleId" />
      <el-table-column label="规则名称" align="center" prop="ruleName" />
      <el-table-column label="迟到判定分钟数" align="center" prop="lateMinute" />
      <el-table-column label="缺勤判定分钟数" align="center" prop="absentCondition" />
      <el-table-column label="早退判定分钟数" align="center" prop="earlyLeaveMinute" />
      <el-table-column label="定位签到范围" align="center" prop="signRange" />
      <el-table-column label="考勤预警阈值" align="center" prop="warningThreshold" />
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
            v-hasPermi="['attendance:attendance-rule:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['attendance:attendance-rule:remove']"
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

    <!-- 添加或修改考勤规则对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="规则名称" prop="ruleName">
          <el-input v-model="form.ruleName" placeholder="请输入规则名称" />
        </el-form-item>
        <el-form-item label="迟到判定分钟数" prop="lateMinute">
          <el-input v-model="form.lateMinute" placeholder="请输入迟到判定分钟数" />
        </el-form-item>
        <el-form-item label="缺勤判定分钟数" prop="absentCondition">
          <el-input v-model="form.absentCondition" placeholder="请输入缺勤判定分钟数" />
        </el-form-item>
        <el-form-item label="早退判定分钟数" prop="earlyLeaveMinute">
          <el-input v-model="form.earlyLeaveMinute" placeholder="请输入早退判定分钟数" />
        </el-form-item>
        <el-form-item label="定位签到范围" prop="signRange">
          <el-input v-model="form.signRange" placeholder="请输入定位签到范围" />
        </el-form-item>
        <el-form-item label="考勤预警阈值" prop="warningThreshold">
          <el-input v-model="form.warningThreshold" placeholder="请输入考勤预警阈值" />
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
import { listAttendanceRule, getAttendanceRule, delAttendanceRule, addAttendanceRule, updateAttendanceRule } from "@/api/attendance/attendance-rule"

export default {
  name: "AttendanceRule",
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
      // 考勤规则表格数据
      attendanceRuleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        ruleName: null,
        lateMinute: null,
        absentCondition: null,
        earlyLeaveMinute: null,
        signRange: null,
        warningThreshold: null,
        status: null,
        updateUserId: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        ruleName: [
          { required: true, message: "规则名称不能为空", trigger: "blur" }
        ],
        lateMinute: [
          { required: true, message: "迟到判定分钟数不能为空", trigger: "blur" }
        ],
        absentCondition: [
          { required: true, message: "缺勤判定分钟数不能为空", trigger: "blur" }
        ],
        earlyLeaveMinute: [
          { required: true, message: "早退判定分钟数不能为空", trigger: "blur" }
        ],
        signRange: [
          { required: true, message: "定位签到范围不能为空", trigger: "blur" }
        ],
        warningThreshold: [
          { required: true, message: "考勤预警阈值不能为空", trigger: "blur" }
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
    /** 查询考勤规则列表 */
    getList() {
      this.loading = true
      listAttendanceRule(this.queryParams).then(response => {
        this.attendanceRuleList = response.rows
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
        ruleId: null,
        ruleName: null,
        lateMinute: null,
        absentCondition: null,
        earlyLeaveMinute: null,
        signRange: null,
        warningThreshold: null,
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
      this.ids = selection.map(item => item.ruleId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加考勤规则"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const ruleId = row.ruleId || this.ids
      getAttendanceRule(ruleId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改考勤规则"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.ruleId != null) {
            updateAttendanceRule(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addAttendanceRule(this.form).then(response => {
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
      const ruleIds = row.ruleId || this.ids
      this.$modal.confirm('是否确认删除考勤规则编号为"' + ruleIds + '"的数据项？').then(function() {
        return delAttendanceRule(ruleIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('attendance/attendance-rule/export', {
        ...this.queryParams
      }, `attendance-rule_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
