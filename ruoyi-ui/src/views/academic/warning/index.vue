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
      <el-form-item label="预警类型" prop="warningType">
        <el-select v-model="queryParams.warningType" placeholder="请选择预警类型" clearable>
          <el-option label="成绩预警" :value="1" />
          <el-option label="考勤预警" :value="2" />
          <el-option label="行为预警" :value="3" />
          <el-option label="其他预警" :value="4" />
        </el-select>
      </el-form-item>
      <el-form-item label="预警级别" prop="warningLevel">
        <el-select v-model="queryParams.warningLevel" placeholder="请选择预警级别" clearable>
          <el-option label="一级预警" :value="1" />
          <el-option label="二级预警" :value="2" />
          <el-option label="三级预警" :value="3" />
        </el-select>
      </el-form-item>
      <el-form-item label="处理状态" prop="handleStatus">
        <el-select v-model="queryParams.handleStatus" placeholder="请选择处理状态" clearable>
          <el-option label="待处理" :value="0" />
          <el-option label="处理中" :value="1" />
          <el-option label="已处理" :value="2" />
          <el-option label="已关闭" :value="3" />
        </el-select>
      </el-form-item>
      <el-form-item label="处理人 ID" prop="handleUserId">
        <el-input
          v-model="queryParams.handleUserId"
          placeholder="请输入处理人 ID"
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
      <el-form-item label="处理时间" prop="handleTime">
        <el-date-picker clearable
                        v-model="queryParams.handleTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择处理时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="审核人 ID" prop="verifyUserId">
        <el-input
          v-model="queryParams.verifyUserId"
          placeholder="请输入审核人 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核时间" prop="verifyTime">
        <el-date-picker clearable
                        v-model="queryParams.verifyTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择审核时间">
        </el-date-picker>
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
          v-hasPermi="['academic:warning:add']"
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
          v-hasPermi="['academic:warning:edit']"
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
          v-hasPermi="['academic:warning:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['academic:warning:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="warningList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="预警 ID" align="center" prop="warningId" />
      <el-table-column label="学生 ID" align="center" prop="studentId" />
      <el-table-column label="预警类型" align="center" prop="warningType">
        <template slot-scope="scope">
          <span v-if="scope.row.warningType === 1">成绩预警</span>
          <span v-else-if="scope.row.warningType === 2">考勤预警</span>
          <span v-else-if="scope.row.warningType === 3">行为预警</span>
          <span v-else-if="scope.row.warningType === 4">其他预警</span>
          <span v-else>{{ scope.row.warningType }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预警级别" align="center" prop="warningLevel">
        <template slot-scope="scope">
          <span v-if="scope.row.warningLevel === 1">一级预警</span>
          <span v-else-if="scope.row.warningLevel === 2">二级预警</span>
          <span v-else-if="scope.row.warningLevel === 3">三级预警</span>
          <span v-else>{{ scope.row.warningLevel }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预警原因" align="center" prop="reason" />
      <el-table-column label="处理状态" align="center" prop="handleStatus">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.handleStatus === 0" type="warning">待处理</el-tag>
          <el-tag v-else-if="scope.row.handleStatus === 1" type="primary">处理中</el-tag>
          <el-tag v-else-if="scope.row.handleStatus === 2" type="success">已处理</el-tag>
          <el-tag v-else-if="scope.row.handleStatus === 3" type="info">已关闭</el-tag>
          <span v-else>{{ scope.row.handleStatus }}</span>
        </template>
      </el-table-column>
      <el-table-column label="处理人 ID" align="center" prop="handleUserId" />
      <el-table-column label="处理备注" align="center" prop="handleRemark" />
      <el-table-column label="更新用户" align="center" prop="updateUserId" />
      <el-table-column label="处理时间" align="center" prop="handleTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.handleTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核人 ID" align="center" prop="verifyUserId" />
      <el-table-column label="审核时间" align="center" prop="verifyTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.verifyTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
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
            v-hasPermi="['academic:warning:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['academic:warning:remove']"
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

    <!-- 添加或修改学业预警对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学生 ID" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学生 ID" />
        </el-form-item>
        <el-form-item label="预警类型" prop="warningType">
          <el-select v-model="form.warningType" placeholder="请选择预警类型">
            <el-option label="成绩预警" :value="1" />
            <el-option label="考勤预警" :value="2" />
            <el-option label="行为预警" :value="3" />
            <el-option label="其他预警" :value="4" />
          </el-select>
        </el-form-item>
        <el-form-item label="预警级别" prop="warningLevel">
          <el-select v-model="form.warningLevel" placeholder="请选择预警级别">
            <el-option label="一级预警" :value="1" />
            <el-option label="二级预警" :value="2" />
            <el-option label="三级预警" :value="3" />
          </el-select>
        </el-form-item>
        <el-form-item label="预警原因" prop="reason">
          <el-input v-model="form.reason" type="textarea" placeholder="请输入预警原因" />
        </el-form-item>
        <el-form-item label="处理状态" prop="handleStatus">
          <el-select v-model="form.handleStatus" placeholder="请选择处理状态">
            <el-option label="待处理" :value="0" />
            <el-option label="处理中" :value="1" />
            <el-option label="已处理" :value="2" />
            <el-option label="已关闭" :value="3" />
          </el-select>
        </el-form-item>
        <el-form-item label="处理人 ID" prop="handleUserId">
          <el-input v-model="form.handleUserId" placeholder="请输入处理人 ID" />
        </el-form-item>
        <el-form-item label="处理备注" prop="handleRemark">
          <el-input v-model="form.handleRemark" type="textarea" placeholder="请输入处理备注" />
        </el-form-item>
        <el-form-item label="更新用户" prop="updateUserId">
          <el-input v-model="form.updateUserId" placeholder="请输入更新用户" />
        </el-form-item>
        <el-form-item label="处理时间" prop="handleTime">
          <el-date-picker clearable
                          v-model="form.handleTime"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="请选择处理时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="审核人 ID" prop="verifyUserId">
          <el-input v-model="form.verifyUserId" placeholder="请输入审核人 ID" />
        </el-form-item>
        <el-form-item label="审核时间" prop="verifyTime">
          <el-date-picker clearable
                          v-model="form.verifyTime"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="请选择审核时间">
          </el-date-picker>
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
import { listWarning, getWarning, delWarning, addWarning, updateWarning } from "@/api/academic/warning"

export default {
  name: "Warning",
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
      // 学业预警表格数据
      warningList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        studentId: null,
        warningType: null,
        warningLevel: null,
        reason: null,
        handleStatus: null,
        handleUserId: null,
        handleRemark: null,
        updateUserId: null,
        handleTime: null,
        verifyUserId: null,
        verifyTime: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        studentId: [
          { required: true, message: "学生 ID不能为空", trigger: "blur" }
        ],
        warningType: [
          { required: true, message: "预警类型不能为空", trigger: "change" }
        ],
        warningLevel: [
          { required: true, message: "预警级别不能为空", trigger: "change" }
        ],
        reason: [
          { required: true, message: "预警原因不能为空", trigger: "blur" }
        ],
        handleStatus: [
          { required: true, message: "处理状态不能为空", trigger: "change" }
        ],
        updateUserId: [
          { required: true, message: "更新用户不能为空", trigger: "blur" }
        ],
        verifyUserId: [
          { required: true, message: "审核人 ID不能为空", trigger: "blur" }
        ],
        verifyTime: [
          { required: true, message: "审核时间不能为空", trigger: "change" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询学业预警列表 */
    getList() {
      this.loading = true
      listWarning(this.queryParams).then(response => {
        this.warningList = response.rows
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
        warningId: null,
        studentId: null,
        warningType: 1, // 默认预警类型
        warningLevel: 1, // 默认预警级别
        reason: null,
        handleStatus: 0, // 默认待处理
        handleUserId: null,
        handleRemark: null,
        createTime: null,
        updateTime: null,
        updateUserId: null,
        handleTime: null,
        verifyUserId: null,
        verifyTime: null,
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
      this.ids = selection.map(item => item.warningId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加学业预警"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const warningId = row.warningId || this.ids
      getWarning(warningId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改学业预警"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          // 确保数字字段类型正确
          if (this.form.warningType) {
            this.form.warningType = Number(this.form.warningType)
          }
          if (this.form.warningLevel) {
            this.form.warningLevel = Number(this.form.warningLevel)
          }
          if (this.form.handleStatus) {
            this.form.handleStatus = Number(this.form.handleStatus)
          }
          if (this.form.isDeleted) {
            this.form.isDeleted = Number(this.form.isDeleted)
          }

          if (this.form.warningId != null) {
            updateWarning(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            }).catch((error) => {
              console.error("修改失败:", error)
              this.$modal.msgError("修改失败")
            })
          } else {
            addWarning(this.form).then(response => {
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
      const warningIds = row.warningId || this.ids
      this.$modal.confirm('是否确认删除学业预警编号为"' + warningIds + '"的数据项？').then(() => {
        return delWarning(warningIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('academic/warning/export', {
        ...this.queryParams
      }, `warning_${new Date().getTime()}.xlsx`)
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
