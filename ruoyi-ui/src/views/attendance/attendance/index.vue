<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="80px">
      <el-form-item label="学生ID" prop="studentId">
        <el-input
          v-model="queryParams.studentId"
          placeholder="请输入学生ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="课程ID" prop="courseId">
        <el-input
          v-model="queryParams.courseId"
          placeholder="请输入课程ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="考勤日期" prop="attendDate">
        <el-date-picker clearable
                        v-model="queryParams.attendDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择考勤日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="考勤类型" prop="attendType">
        <el-select v-model="queryParams.attendType" placeholder="请选择考勤类型" clearable>
          <el-option label="正常" :value="1" />
          <el-option label="迟到" :value="2" />
          <el-option label="早退" :value="3" />
          <el-option label="缺勤" :value="4" />
          <el-option label="请假" :value="5" />
        </el-select>
      </el-form-item>
      <el-form-item label="签到时间" prop="signTime">
        <el-date-picker clearable
                        v-model="queryParams.signTime"
                        type="datetime"
                        value-format="yyyy-MM-dd HH:mm:ss"
                        placeholder="请选择签到时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="补签申请情况" prop="supplementApply">
        <el-select v-model="queryParams.supplementApply" placeholder="请选择补签申请情况" clearable>
          <el-option label="未申请" :value="0" />
          <el-option label="已申请" :value="1" />
        </el-select>
      </el-form-item>
      <el-form-item label="补签处理情况" prop="supplementResult">
        <el-select v-model="queryParams.supplementResult" placeholder="请选择补签处理情况" clearable>
          <el-option label="未处理" :value="0" />
          <el-option label="已通过" :value="1" />
          <el-option label="已拒绝" :value="2" />
        </el-select>
      </el-form-item>
      <el-form-item label="审批教师ID" prop="approveUserId">
        <el-input
          v-model="queryParams.approveUserId"
          placeholder="请输入审批教师ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审批时间" prop="approveTime">
        <el-date-picker clearable
                        v-model="queryParams.approveTime"
                        type="datetime"
                        value-format="yyyy-MM-dd HH:mm:ss"
                        placeholder="请选择审批时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="学期" prop="semester">
        <el-input
          v-model="queryParams.semester"
          placeholder="请输入学期"
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
          v-hasPermi="['attendance:attendance:add']"
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
          v-hasPermi="['attendance:attendance:edit']"
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
          v-hasPermi="['attendance:attendance:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['attendance:attendance:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="attendanceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="考勤ID" align="center" prop="attendId" width="80" />
      <el-table-column label="学生ID" align="center" prop="studentId" width="100" />
      <el-table-column label="课程ID" align="center" prop="courseId" width="80" />
      <el-table-column label="考勤日期" align="center" prop="attendDate" width="120">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.attendDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="考勤类型" align="center" prop="attendType" width="80">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.attendType === 1" type="success">正常</el-tag>
          <el-tag v-else-if="scope.row.attendType === 2" type="warning">迟到</el-tag>
          <el-tag v-else-if="scope.row.attendType === 3" type="warning">早退</el-tag>
          <el-tag v-else-if="scope.row.attendType === 4" type="danger">缺勤</el-tag>
          <el-tag v-else-if="scope.row.attendType === 5" type="info">请假</el-tag>
          <span v-else>{{ scope.row.attendType }}</span>
        </template>
      </el-table-column>
      <el-table-column label="签到时间" align="center" prop="signTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.signTime, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="补签申请情况" align="center" prop="supplementApply" width="100">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.supplementApply === 0" type="info">未申请</el-tag>
          <el-tag v-else-if="scope.row.supplementApply === 1" type="warning">已申请</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="补签理由" align="center" prop="supplementReason" min-width="120" show-overflow-tooltip />
      <el-table-column label="补签处理情况" align="center" prop="supplementResult" width="100">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.supplementResult === 0" type="info">未处理</el-tag>
          <el-tag v-else-if="scope.row.supplementResult === 1" type="success">已通过</el-tag>
          <el-tag v-else-if="scope.row.supplementResult === 2" type="danger">已拒绝</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="审批教师ID" align="center" prop="approveUserId" width="100" />
      <el-table-column label="审批时间" align="center" prop="approveTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.approveTime, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="学期" align="center" prop="semester" width="120" />
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
            v-hasPermi="['attendance:attendance:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['attendance:attendance:remove']"
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

    <!-- 添加或修改考勤管理主表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="学生ID" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学生ID" />
        </el-form-item>
        <el-form-item label="课程ID" prop="courseId">
          <el-input v-model="form.courseId" placeholder="请输入课程ID" type="number" />
        </el-form-item>
        <el-form-item label="考勤日期" prop="attendDate">
          <el-date-picker clearable
                          v-model="form.attendDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择考勤日期"
                          style="width: 100%">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="考勤类型" prop="attendType">
          <el-radio-group v-model="form.attendType">
            <el-radio :label="1">正常</el-radio>
            <el-radio :label="2">迟到</el-radio>
            <el-radio :label="3">早退</el-radio>
            <el-radio :label="4">缺勤</el-radio>
            <el-radio :label="5">请假</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="签到时间" prop="signTime">
          <el-date-picker clearable
                          v-model="form.signTime"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="请选择签到时间"
                          style="width: 100%">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="补签申请情况" prop="supplementApply">
          <el-radio-group v-model="form.supplementApply">
            <el-radio :label="0">未申请</el-radio>
            <el-radio :label="1">已申请</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="补签理由" prop="supplementReason">
          <el-input v-model="form.supplementReason" type="textarea" placeholder="请输入补签理由" :rows="3" />
        </el-form-item>
        <el-form-item label="补签处理情况" prop="supplementResult">
          <el-radio-group v-model="form.supplementResult">
            <el-radio :label="0">未处理</el-radio>
            <el-radio :label="1">已通过</el-radio>
            <el-radio :label="2">已拒绝</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="审批教师ID" prop="approveUserId">
          <el-input v-model="form.approveUserId" placeholder="请输入审批教师ID" />
        </el-form-item>
        <el-form-item label="审批时间" prop="approveTime">
          <el-date-picker clearable
                          v-model="form.approveTime"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="请选择审批时间"
                          style="width: 100%">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="学期" prop="semester">
          <el-input v-model="form.semester" placeholder="请输入学期" />
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
import { listAttendance, getAttendance, delAttendance, addAttendance, updateAttendance } from "@/api/attendance/attendance"

export default {
  name: "Attendance",
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
      // 考勤管理主表表格数据
      attendanceList: [],
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
        attendDate: null,
        attendType: null,
        signTime: null,
        supplementApply: null,
        supplementReason: null,
        supplementResult: null,
        approveUserId: null,
        approveTime: null,
        semester: null,
        updateUserId: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        studentId: [
          { required: true, message: "学生ID不能为空", trigger: "blur" }
        ],
        courseId: [
          { required: true, message: "课程ID不能为空", trigger: "blur" },
          { validator: validateNumber, trigger: "blur" }
        ],
        attendDate: [
          { required: true, message: "考勤日期不能为空", trigger: "change" }
        ],
        attendType: [
          { required: true, message: "考勤类型不能为空", trigger: "change" }
        ],
        supplementApply: [
          { required: true, message: "补签申请情况不能为空", trigger: "change" }
        ],
        supplementResult: [
          { required: true, message: "补签处理情况不能为空", trigger: "change" }
        ],
        semester: [
          { required: true, message: "学期不能为空", trigger: "blur" }
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
    /** 查询考勤管理主表列表 */
    getList() {
      this.loading = true
      listAttendance(this.queryParams).then(response => {
        this.attendanceList = response.rows
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
        attendId: null,
        studentId: null,
        courseId: null,
        attendDate: null,
        attendType: 1, // 默认正常
        signTime: null,
        supplementApply: 0, // 默认未申请
        supplementReason: null,
        supplementResult: 0, // 默认未处理
        approveUserId: null,
        approveTime: null,
        semester: null,
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
      this.ids = selection.map(item => item.attendId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加考勤记录"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const attendId = row.attendId || this.ids[0]
      getAttendance(attendId).then(response => {
        this.form = response.data
        // 确保数字字段类型正确
        if (this.form.attendType) this.form.attendType = Number(this.form.attendType)
        if (this.form.supplementApply) this.form.supplementApply = Number(this.form.supplementApply)
        if (this.form.supplementResult) this.form.supplementResult = Number(this.form.supplementResult)
        if (this.form.isDeleted) this.form.isDeleted = Number(this.form.isDeleted)
        if (this.form.courseId) this.form.courseId = Number(this.form.courseId)

        this.open = true
        this.title = "修改考勤记录"
      }).catch(error => {
        console.error("获取考勤信息失败:", error)
        this.$modal.msgError("获取考勤信息失败")
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          // 数据类型转换
          const submitData = {
            ...this.form,
            attendType: Number(this.form.attendType),
            supplementApply: Number(this.form.supplementApply),
            supplementResult: Number(this.form.supplementResult),
            isDeleted: Number(this.form.isDeleted || 0)
          }

          // 转换ID字段为数字
          if (this.form.courseId) submitData.courseId = Number(this.form.courseId)

          if (submitData.attendId != null) {
            updateAttendance(submitData).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            }).catch(error => {
              console.error("修改失败:", error)
              this.$modal.msgError("修改失败")
            })
          } else {
            addAttendance(submitData).then(response => {
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
      const attendIds = row.attendId || this.ids
      this.$modal.confirm('是否确认删除考勤记录编号为"' + attendIds + '"的数据项？').then(() => {
        return delAttendance(attendIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('attendance/attendance/export', {
        ...this.queryParams
      }, `attendance_${new Date().getTime()}.xlsx`)
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
