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
      <el-form-item label="专业 ID" prop="majorId">
        <el-input
          v-model="queryParams.majorId"
          placeholder="请输入专业 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="投递 ID" prop="deliveryId">
        <el-input
          v-model="queryParams.deliveryId"
          placeholder="请输入投递 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="毕业年份" prop="graduationYear">
        <el-input
          v-model="queryParams.graduationYear"
          placeholder="请输入毕业年份"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="就业企业 ID" prop="enterpriseId">
        <el-input
          v-model="queryParams.enterpriseId"
          placeholder="请输入就业企业 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="就业岗位" prop="position">
        <el-input
          v-model="queryParams.position"
          placeholder="请输入就业岗位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工作城市" prop="workCity">
        <el-input
          v-model="queryParams.workCity"
          placeholder="请输入工作城市"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="月薪" prop="salary">
        <el-input
          v-model="queryParams.salary"
          placeholder="请输入月薪"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="合同开始时间" prop="contractStart">
        <el-date-picker clearable
          v-model="queryParams.contractStart"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择合同开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="合同结束时间" prop="contractEnd">
        <el-date-picker clearable
          v-model="queryParams.contractEnd"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择合同结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="收到 offer 时间" prop="offerTime">
        <el-date-picker clearable
          v-model="queryParams.offerTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择收到 offer 时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="就业渠道" prop="employmentSource">
        <el-input
          v-model="queryParams.employmentSource"
          placeholder="请输入就业渠道"
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
      <el-form-item label="逻辑删除状态" prop="isDeleted">
        <el-input
          v-model="queryParams.isDeleted"
          placeholder="请输入逻辑删除状态"
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
          v-hasPermi="['employ:employ_studentInfo:add']"
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
          v-hasPermi="['employ:employ_studentInfo:edit']"
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
          v-hasPermi="['employ:employ_studentInfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['employ:employ_studentInfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="employ_studentInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="就业 ID" align="center" prop="employmentId" />
      <el-table-column label="学生 ID" align="center" prop="studentId" />
      <el-table-column label="专业 ID" align="center" prop="majorId" />
      <el-table-column label="投递 ID" align="center" prop="deliveryId" />
      <el-table-column label="毕业年份" align="center" prop="graduationYear" />
      <el-table-column label="就业状态" align="center" prop="employmentStatus" />
      <el-table-column label="就业企业 ID" align="center" prop="enterpriseId" />
      <el-table-column label="就业岗位" align="center" prop="position" />
      <el-table-column label="工作城市" align="center" prop="workCity" />
      <el-table-column label="月薪" align="center" prop="salary" />
      <el-table-column label="合同类型" align="center" prop="contractType" />
      <el-table-column label="合同开始时间" align="center" prop="contractStart" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.contractStart, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="合同结束时间" align="center" prop="contractEnd" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.contractEnd, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="收到 offer 时间" align="center" prop="offerTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.offerTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="就业渠道" align="center" prop="employmentSource" />
      <el-table-column label="更新用户" align="center" prop="updateUserId" />
      <el-table-column label="逻辑删除状态" align="center" prop="isDeleted" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['employ:employ_studentInfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['employ:employ_studentInfo:remove']"
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

    <!-- 添加或修改学生就业信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学生 ID" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学生 ID" />
        </el-form-item>
        <el-form-item label="专业 ID" prop="majorId">
          <el-input v-model="form.majorId" placeholder="请输入专业 ID" />
        </el-form-item>
        <el-form-item label="投递 ID" prop="deliveryId">
          <el-input v-model="form.deliveryId" placeholder="请输入投递 ID" />
        </el-form-item>
        <el-form-item label="毕业年份" prop="graduationYear">
          <el-input v-model="form.graduationYear" placeholder="请输入毕业年份" />
        </el-form-item>
        <el-form-item label="就业企业 ID" prop="enterpriseId">
          <el-input v-model="form.enterpriseId" placeholder="请输入就业企业 ID" />
        </el-form-item>
        <el-form-item label="就业岗位" prop="position">
          <el-input v-model="form.position" placeholder="请输入就业岗位" />
        </el-form-item>
        <el-form-item label="工作城市" prop="workCity">
          <el-input v-model="form.workCity" placeholder="请输入工作城市" />
        </el-form-item>
        <el-form-item label="月薪" prop="salary">
          <el-input v-model="form.salary" placeholder="请输入月薪" />
        </el-form-item>
        <el-form-item label="合同开始时间" prop="contractStart">
          <el-date-picker clearable
            v-model="form.contractStart"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择合同开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="合同结束时间" prop="contractEnd">
          <el-date-picker clearable
            v-model="form.contractEnd"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择合同结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="收到 offer 时间" prop="offerTime">
          <el-date-picker clearable
            v-model="form.offerTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择收到 offer 时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="就业渠道" prop="employmentSource">
          <el-input v-model="form.employmentSource" placeholder="请输入就业渠道" />
        </el-form-item>
        <el-form-item label="更新用户" prop="updateUserId">
          <el-input v-model="form.updateUserId" placeholder="请输入更新用户" />
        </el-form-item>
        <el-form-item label="逻辑删除状态" prop="isDeleted">
          <el-input v-model="form.isDeleted" placeholder="请输入逻辑删除状态" />
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
import { listEmploy_studentInfo, getEmploy_studentInfo, delEmploy_studentInfo, addEmploy_studentInfo, updateEmploy_studentInfo } from "@/api/employ/employ_studentInfo"

export default {
  name: "Employ_studentInfo",
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
      // 学生就业信息表格数据
      employ_studentInfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        studentId: null,
        majorId: null,
        deliveryId: null,
        graduationYear: null,
        employmentStatus: null,
        enterpriseId: null,
        position: null,
        workCity: null,
        salary: null,
        contractType: null,
        contractStart: null,
        contractEnd: null,
        offerTime: null,
        employmentSource: null,
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
        majorId: [
          { required: true, message: "专业 ID不能为空", trigger: "blur" }
        ],
        deliveryId: [
          { required: true, message: "投递 ID不能为空", trigger: "blur" }
        ],
        graduationYear: [
          { required: true, message: "毕业年份不能为空", trigger: "blur" }
        ],
        employmentStatus: [
          { required: true, message: "就业状态不能为空", trigger: "change" }
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
          { required: true, message: "逻辑删除状态不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询学生就业信息列表 */
    getList() {
      this.loading = true
      listEmploy_studentInfo(this.queryParams).then(response => {
        this.employ_studentInfoList = response.rows
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
        employmentId: null,
        studentId: null,
        majorId: null,
        deliveryId: null,
        graduationYear: null,
        employmentStatus: null,
        enterpriseId: null,
        position: null,
        workCity: null,
        salary: null,
        contractType: null,
        contractStart: null,
        contractEnd: null,
        offerTime: null,
        employmentSource: null,
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
      this.ids = selection.map(item => item.employmentId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加学生就业信息"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const employmentId = row.employmentId || this.ids
      getEmploy_studentInfo(employmentId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改学生就业信息"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.employmentId != null) {
            updateEmploy_studentInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addEmploy_studentInfo(this.form).then(response => {
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
      const employmentIds = row.employmentId || this.ids
      this.$modal.confirm('是否确认删除学生就业信息编号为"' + employmentIds + '"的数据项？').then(function() {
        return delEmploy_studentInfo(employmentIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('employ/employ_studentInfo/export', {
        ...this.queryParams
      }, `employ_studentInfo_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
