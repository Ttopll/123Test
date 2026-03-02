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
      <el-form-item label="学生 ID" prop="studentId">
        <el-input
          v-model="queryParams.studentId"
          placeholder="请输入学生 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="报名时间" prop="signupTime">
        <el-date-picker clearable
          v-model="queryParams.signupTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择报名时间">
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
      <el-form-item label="签到时间" prop="signinTime">
        <el-date-picker clearable
          v-model="queryParams.signinTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择签到时间">
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
          v-hasPermi="['practice:practice_signup:add']"
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
          v-hasPermi="['practice:practice_signup:edit']"
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
          v-hasPermi="['practice:practice_signup:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['practice:practice_signup:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="practice_signupList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="报名 ID" align="center" prop="signupId" />
      <el-table-column label="活动 ID" align="center" prop="activityId" />
      <el-table-column label="学生 ID" align="center" prop="studentId" />
      <el-table-column label="报名时间" align="center" prop="signupTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.signupTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="报名材料 URL" align="center" prop="materialUrls" />
      <el-table-column label="审核状态" align="center" prop="auditStatus" />
      <el-table-column label="审核人 ID" align="center" prop="auditUserId" />
      <el-table-column label="审核时间" align="center" prop="auditTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.auditTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核备注" align="center" prop="auditRemark" />
      <el-table-column label="参与状态" align="center" prop="participateStatus" />
      <el-table-column label="签到时间" align="center" prop="signinTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.signinTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
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
            v-hasPermi="['practice:practice_signup:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['practice:practice_signup:remove']"
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

    <!-- 添加或修改实践活动报名对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="活动 ID" prop="activityId">
          <el-input v-model="form.activityId" placeholder="请输入活动 ID" />
        </el-form-item>
        <el-form-item label="学生 ID" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学生 ID" />
        </el-form-item>
        <el-form-item label="报名时间" prop="signupTime">
          <el-date-picker clearable
            v-model="form.signupTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择报名时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="报名材料 URL" prop="materialUrls">
          <el-input v-model="form.materialUrls" type="textarea" placeholder="请输入内容" />
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
        <el-form-item label="签到时间" prop="signinTime">
          <el-date-picker clearable
            v-model="form.signinTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择签到时间">
          </el-date-picker>
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
import { listPractice_signup, getPractice_signup, delPractice_signup, addPractice_signup, updatePractice_signup } from "@/api/practice/practice_signup"

export default {
  name: "Practice_signup",
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
      // 实践活动报名表格数据
      practice_signupList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        activityId: null,
        studentId: null,
        signupTime: null,
        materialUrls: null,
        auditStatus: null,
        auditUserId: null,
        auditTime: null,
        auditRemark: null,
        participateStatus: null,
        signinTime: null,
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
        studentId: [
          { required: true, message: "学生 ID不能为空", trigger: "blur" }
        ],
        signupTime: [
          { required: true, message: "报名时间不能为空", trigger: "blur" }
        ],
        auditStatus: [
          { required: true, message: "审核状态不能为空", trigger: "change" }
        ],
        participateStatus: [
          { required: true, message: "参与状态不能为空", trigger: "change" }
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
    /** 查询实践活动报名列表 */
    getList() {
      this.loading = true
      listPractice_signup(this.queryParams).then(response => {
        this.practice_signupList = response.rows
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
        signupId: null,
        activityId: null,
        studentId: null,
        signupTime: null,
        materialUrls: null,
        auditStatus: null,
        auditUserId: null,
        auditTime: null,
        auditRemark: null,
        participateStatus: null,
        signinTime: null,
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
      this.ids = selection.map(item => item.signupId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加实践活动报名"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const signupId = row.signupId || this.ids
      getPractice_signup(signupId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改实践活动报名"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.signupId != null) {
            updatePractice_signup(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addPractice_signup(this.form).then(response => {
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
      const signupIds = row.signupId || this.ids
      this.$modal.confirm('是否确认删除实践活动报名编号为"' + signupIds + '"的数据项？').then(function() {
        return delPractice_signup(signupIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('practice/practice_signup/export', {
        ...this.queryParams
      }, `practice_signup_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
