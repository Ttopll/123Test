<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="学生ID" prop="studentId">
        <el-input
          v-model="queryParams.studentId"
          placeholder="请输入学生ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预警类型" prop="warningType">
        <el-select v-model="queryParams.warningType" placeholder="请选择预警类型" clearable>
          <el-option label="情绪问题" :value="1" />
          <el-option label="学习压力" :value="2" />
          <el-option label="人际关系" :value="3" />
          <el-option label="行为异常" :value="4" />
          <el-option label="其他心理问题" :value="5" />
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
      <el-form-item label="心理辅导人ID" prop="handleUserId">
        <el-input
          v-model="queryParams.handleUserId"
          placeholder="请输入心理辅导人ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更新用户" prop="updateUserId">
        <el-input
          v-model="queryParams.updateUserId"
          placeholder="请输入更新用户ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="处理时间" prop="handleTime">
        <el-date-picker clearable
                        v-model="queryParams.handleTime"
                        type="daterange"
                        range-separator="至"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期"
                        value-format="yyyy-MM-dd">
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
          v-hasPermi="['academic:mental-warning:add']"
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
          v-hasPermi="['academic:mental-warning:edit']"
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
          v-hasPermi="['academic:mental-warning:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['academic:mental-warning:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="mentalWarningList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="预警ID" align="center" prop="warningId" width="80" />
      <el-table-column label="学生ID" align="center" prop="studentId" width="100" />
      <el-table-column label="预警类型" align="center" prop="warningType" width="120">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.warningType === 1" type="danger">情绪问题</el-tag>
          <el-tag v-else-if="scope.row.warningType === 2" type="warning">学习压力</el-tag>
          <el-tag v-else-if="scope.row.warningType === 3" type="info">人际关系</el-tag>
          <el-tag v-else-if="scope.row.warningType === 4">行为异常</el-tag>
          <el-tag v-else-if="scope.row.warningType === 5">其他心理问题</el-tag>
          <span v-else>{{ scope.row.warningType }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预警级别" align="center" prop="warningLevel" width="100">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.warningLevel === 1" type="danger">一级预警</el-tag>
          <el-tag v-else-if="scope.row.warningLevel === 2" type="warning">二级预警</el-tag>
          <el-tag v-else-if="scope.row.warningLevel === 3">三级预警</el-tag>
          <span v-else>{{ scope.row.warningLevel }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预警原因" align="center" prop="reason" min-width="150" show-overflow-tooltip />
      <el-table-column label="处理状态" align="center" prop="handleStatus" width="100">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.handleStatus === 0" type="warning">待处理</el-tag>
          <el-tag v-else-if="scope.row.handleStatus === 1" type="primary">处理中</el-tag>
          <el-tag v-else-if="scope.row.handleStatus === 2" type="success">已处理</el-tag>
          <el-tag v-else-if="scope.row.handleStatus === 3" type="info">已关闭</el-tag>
          <span v-else>{{ scope.row.handleStatus }}</span>
        </template>
      </el-table-column>
      <el-table-column label="心理辅导人ID" align="center" prop="handleUserId" width="120" />
      <el-table-column label="辅导记录" align="center" prop="handleRemark" min-width="150" show-overflow-tooltip />
      <el-table-column label="处理时间" align="center" prop="handleTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.handleTime, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新用户" align="center" prop="updateUserId" width="100" />
      <el-table-column label="删除状态" align="center" prop="isDeleted" width="80">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.isDeleted === 0" type="success">正常</el-tag>
          <el-tag v-else-if="scope.row.isDeleted === 1" type="info">删除</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="150">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['academic:mental-warning:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['academic:mental-warning:remove']"
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

    <!-- 添加或修改心理健康预警对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="学生ID" prop="studentId">
              <el-input v-model="form.studentId" placeholder="请输入学生ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="预警类型" prop="warningType">
              <el-select v-model="form.warningType" placeholder="请选择预警类型" style="width: 100%">
                <el-option label="情绪问题" :value="1" />
                <el-option label="学习压力" :value="2" />
                <el-option label="人际关系" :value="3" />
                <el-option label="行为异常" :value="4" />
                <el-option label="其他心理问题" :value="5" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="预警级别" prop="warningLevel">
              <el-select v-model="form.warningLevel" placeholder="请选择预警级别" style="width: 100%">
                <el-option label="一级预警" :value="1" />
                <el-option label="二级预警" :value="2" />
                <el-option label="三级预警" :value="3" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="处理状态" prop="handleStatus">
              <el-select v-model="form.handleStatus" placeholder="请选择处理状态" style="width: 100%">
                <el-option label="待处理" :value="0" />
                <el-option label="处理中" :value="1" />
                <el-option label="已处理" :value="2" />
                <el-option label="已关闭" :value="3" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="预警原因" prop="reason">
          <el-input v-model="form.reason" type="textarea" placeholder="请输入预警原因" :rows="3" />
        </el-form-item>

        <el-row>
          <el-col :span="12">
            <el-form-item label="心理辅导人ID" prop="handleUserId">
              <el-input v-model="form.handleUserId" placeholder="请输入心理辅导人ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="更新用户" prop="updateUserId">
              <el-input v-model="form.updateUserId" placeholder="请输入更新用户ID" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="辅导记录" prop="handleRemark">
          <el-input v-model="form.handleRemark" type="textarea" placeholder="请输入辅导记录" :rows="3" />
        </el-form-item>

        <el-form-item label="处理时间" prop="handleTime">
          <el-date-picker clearable
                          v-model="form.handleTime"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="请选择处理时间"
                          style="width: 100%">
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
import { listMentalWarning, getMentalWarning, delMentalWarning, addMentalWarning, updateMentalWarning } from "@/api/academic/mental-warning"

export default {
  name: "MentalWarning",
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
      // 心理健康预警表格数据
      mentalWarningList: [],
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
        handleTime: null,
        updateUserId: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        studentId: [
          { required: true, message: "学生ID不能为空", trigger: "blur" },
          { validator: validateNumber, trigger: "blur" }
        ],
        warningType: [
          { required: true, message: "预警类型不能为空", trigger: "change" }
        ],
        warningLevel: [
          { required: true, message: "预警级别不能为空", trigger: "change" }
        ],
        reason: [
          { required: true, message: "预警原因不能为空", trigger: "blur" },
          { min: 1, max: 500, message: "长度在 1 到 500 个字符", trigger: "blur" }
        ],
        handleStatus: [
          { required: true, message: "处理状态不能为空", trigger: "change" }
        ],
        handleUserId: [
          { validator: validateNumber, trigger: "blur" }
        ],
        updateUserId: [
          { required: true, message: "更新用户不能为空", trigger: "blur" },
          { validator: validateNumber, trigger: "blur" }
        ],
        handleRemark: [
          { max: 500, message: "长度不能超过 500 个字符", trigger: "blur" }
        ],
        handleTime: [
          { required: true, message: "处理时间不能为空", trigger: "change" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询心理健康预警列表 */
    getList() {
      this.loading = true
      // 处理时间范围查询
      const params = {
        ...this.queryParams,
        handleTimeBegin: this.queryParams.handleTime && this.queryParams.handleTime[0],
        handleTimeEnd: this.queryParams.handleTime && this.queryParams.handleTime[1]
      }
      // 移除原始的时间范围字段
      delete params.handleTime

      listMentalWarning(params).then(response => {
        this.mentalWarningList = response.rows
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
        reason: '',
        handleStatus: 0, // 默认待处理
        handleUserId: null,
        handleRemark: '',
        updateUserId: null,
        handleTime: null,
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
      this.queryParams = {
        pageNum: 1,
        pageSize: 10,
        studentId: null,
        warningType: null,
        warningLevel: null,
        reason: null,
        handleStatus: null,
        handleUserId: null,
        handleRemark: null,
        handleTime: null,
        updateUserId: null,
        isDeleted: null
      }
      this.getList()
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
      this.title = "添加心理健康预警"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const warningId = row.warningId || this.ids[0]
      getMentalWarning(warningId).then(response => {
        this.form = response.data
        // 确保数字字段类型正确
        if (this.form.warningType) this.form.warningType = Number(this.form.warningType)
        if (this.form.warningLevel) this.form.warningLevel = Number(this.form.warningLevel)
        if (this.form.handleStatus) this.form.handleStatus = Number(this.form.handleStatus)
        if (this.form.isDeleted) this.form.isDeleted = Number(this.form.isDeleted)

        this.open = true
        this.title = "修改心理健康预警"
      }).catch(error => {
        console.error("获取预警信息失败:", error)
        this.$modal.msgError("获取预警信息失败")
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          // 数据类型转换
          const submitData = {
            ...this.form,
            warningType: Number(this.form.warningType),
            warningLevel: Number(this.form.warningLevel),
            handleStatus: Number(this.form.handleStatus),
            isDeleted: Number(this.form.isDeleted || 0)
          }

          // 转换ID字段为数字
          if (this.form.studentId) submitData.studentId = Number(this.form.studentId)
          if (this.form.handleUserId) submitData.handleUserId = Number(this.form.handleUserId)
          if (this.form.updateUserId) submitData.updateUserId = Number(this.form.updateUserId)

          if (submitData.warningId != null) {
            updateMentalWarning(submitData).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            }).catch(error => {
              console.error("修改失败:", error)
              this.$modal.msgError("修改失败")
            })
          } else {
            addMentalWarning(submitData).then(response => {
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
      const warningIds = row.warningId || this.ids
      this.$modal.confirm('是否确认删除心理健康预警编号为"' + warningIds + '"的数据项？').then(() => {
        return delMentalWarning(warningIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      const params = {
        ...this.queryParams,
        handleTimeBegin: this.queryParams.handleTime && this.queryParams.handleTime[0],
        handleTimeEnd: this.queryParams.handleTime && this.queryParams.handleTime[1]
      }
      delete params.handleTime

      this.download('academic/mental-warning/export', params, `mental-warning_${new Date().getTime()}.xlsx`)
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
.el-table .warning-row {
  background: oldlace;
}
.el-table .success-row {
  background: #f0f9eb;
}
</style>
