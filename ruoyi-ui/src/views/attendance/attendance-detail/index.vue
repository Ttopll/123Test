<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="80px">
      <el-form-item label="考勤ID" prop="attendId">
        <el-input
          v-model="queryParams.attendId"
          placeholder="请输入考勤ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="签到方式" prop="signType">
        <el-select v-model="queryParams.signType" placeholder="请选择签到方式" clearable>
          <el-option label="人脸识别" :value="1" />
          <el-option label="指纹识别" :value="2" />
          <el-option label="二维码" :value="3" />
          <el-option label="位置签到" :value="4" />
          <el-option label="手动签到" :value="5" />
        </el-select>
      </el-form-item>
      <el-form-item label="签到位置" prop="location">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入签到位置"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="二维码标识" prop="qrCode">
        <el-input
          v-model="queryParams.qrCode"
          placeholder="请输入二维码标识"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备信息" prop="deviceInfo">
        <el-input
          v-model="queryParams.deviceInfo"
          placeholder="请输入设备信息"
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
          v-hasPermi="['attendance:attendance-detail:add']"
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
          v-hasPermi="['attendance:attendance-detail:edit']"
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
          v-hasPermi="['attendance:attendance-detail:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['attendance:attendance-detail:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="attendanceDetailList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="详情ID" align="center" prop="detailId" width="80" />
      <el-table-column label="考勤ID" align="center" prop="attendId" width="80" />
      <el-table-column label="签到方式" align="center" prop="signType" width="100">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.signType === 1" type="primary">人脸识别</el-tag>
          <el-tag v-else-if="scope.row.signType === 2" type="success">指纹识别</el-tag>
          <el-tag v-else-if="scope.row.signType === 3" type="warning">二维码</el-tag>
          <el-tag v-else-if="scope.row.signType === 4" type="info">位置签到</el-tag>
          <el-tag v-else-if="scope.row.signType === 5" type="default">手动签到</el-tag>
          <span v-else>{{ scope.row.signType }}</span>
        </template>
      </el-table-column>
      <el-table-column label="签到位置" align="center" prop="location" min-width="120" show-overflow-tooltip />
      <el-table-column label="人脸图片URL" align="center" prop="faceImage" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.faceImage" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="指纹图片URL" align="center" prop="fingerprintImage" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.fingerprintImage" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="二维码标识" align="center" prop="qrCode" width="100" show-overflow-tooltip />
      <el-table-column label="设备信息" align="center" prop="deviceInfo" min-width="120" show-overflow-tooltip />
      <el-table-column label="备注" align="center" prop="remark" min-width="120" show-overflow-tooltip />
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
            v-hasPermi="['attendance:attendance-detail:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['attendance:attendance-detail:remove']"
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

    <!-- 添加或修改考勤详情对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="考勤ID" prop="attendId">
          <el-input v-model="form.attendId" placeholder="请输入考勤ID" type="number" />
        </el-form-item>
        <el-form-item label="签到方式" prop="signType">
          <el-radio-group v-model="form.signType">
            <el-radio :label="1">人脸识别</el-radio>
            <el-radio :label="2">指纹识别</el-radio>
            <el-radio :label="3">二维码</el-radio>
            <el-radio :label="4">位置签到</el-radio>
            <el-radio :label="5">手动签到</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="签到位置" prop="location">
          <el-input v-model="form.location" placeholder="请输入签到位置" />
        </el-form-item>
        <el-form-item label="人脸图片URL" prop="faceImage">
          <image-upload v-model="form.faceImage"/>
        </el-form-item>
        <el-form-item label="指纹图片URL" prop="fingerprintImage">
          <image-upload v-model="form.fingerprintImage"/>
        </el-form-item>
        <el-form-item label="二维码标识" prop="qrCode">
          <el-input v-model="form.qrCode" placeholder="请输入二维码标识" />
        </el-form-item>
        <el-form-item label="设备信息" prop="deviceInfo">
          <el-input v-model="form.deviceInfo" placeholder="请输入设备信息" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入备注" :rows="3" />
        </el-form-item>
        <el-form-item label="更新用户" prop="updateUserId">
          <el-input v-model="form.updateUserId" placeholder="请输入更新用户ID" type="number" />
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
import { listAttendanceDetail, getAttendanceDetail, delAttendanceDetail, addAttendanceDetail, updateAttendanceDetail } from "@/api/attendance/attendance-detail"

export default {
  name: "AttendanceDetail",
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
      // 考勤详情表格数据
      attendanceDetailList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        attendId: null,
        signType: null,
        location: null,
        faceImage: null,
        fingerprintImage: null,
        qrCode: null,
        deviceInfo: null,
        updateUserId: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        attendId: [
          { required: true, message: "考勤ID不能为空", trigger: "blur" },
          { validator: validateNumber, trigger: "blur" }
        ],
        signType: [
          { required: true, message: "签到方式不能为空", trigger: "change" }
        ],
        location: [
          { max: 255, message: "长度不能超过 255 个字符", trigger: "blur" }
        ],
        faceImage: [
          { max: 255, message: "长度不能超过 255 个字符", trigger: "blur" }
        ],
        fingerprintImage: [
          { max: 255, message: "长度不能超过 255 个字符", trigger: "blur" }
        ],
        qrCode: [
          { max: 100, message: "长度不能超过 100 个字符", trigger: "blur" }
        ],
        deviceInfo: [
          { max: 200, message: "长度不能超过 200 个字符", trigger: "blur" }
        ],
        remark: [
          { max: 200, message: "长度不能超过 200 个字符", trigger: "blur" }
        ],
        updateUserId: [
          { required: true, message: "更新用户不能为空", trigger: "blur" },
          { validator: validateNumber, trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询考勤详情列表 */
    getList() {
      this.loading = true
      listAttendanceDetail(this.queryParams).then(response => {
        this.attendanceDetailList = response.rows
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
        detailId: null,
        attendId: null,
        signType: 1, // 默认人脸识别
        location: null,
        faceImage: null,
        fingerprintImage: null,
        qrCode: null,
        deviceInfo: null,
        remark: null,
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
      this.ids = selection.map(item => item.detailId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加考勤详情"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const detailId = row.detailId || this.ids[0]
      getAttendanceDetail(detailId).then(response => {
        this.form = response.data
        // 确保数字字段类型正确
        if (this.form.signType) this.form.signType = Number(this.form.signType)
        if (this.form.isDeleted) this.form.isDeleted = Number(this.form.isDeleted)
        if (this.form.attendId) this.form.attendId = Number(this.form.attendId)
        if (this.form.updateUserId) this.form.updateUserId = Number(this.form.updateUserId)

        this.open = true
        this.title = "修改考勤详情"
      }).catch(error => {
        console.error("获取考勤详情失败:", error)
        this.$modal.msgError("获取考勤详情失败")
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          // 数据类型转换
          const submitData = {
            ...this.form,
            signType: Number(this.form.signType),
            isDeleted: Number(this.form.isDeleted || 0)
          }

          // 转换ID字段为数字
          if (this.form.attendId) submitData.attendId = Number(this.form.attendId)
          if (this.form.updateUserId) submitData.updateUserId = Number(this.form.updateUserId)

          if (submitData.detailId != null) {
            updateAttendanceDetail(submitData).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            }).catch(error => {
              console.error("修改失败:", error)
              this.$modal.msgError("修改失败")
            })
          } else {
            addAttendanceDetail(submitData).then(response => {
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
      const detailIds = row.detailId || this.ids
      this.$modal.confirm('是否确认删除考勤详情编号为"' + detailIds + '"的数据项？').then(() => {
        return delAttendanceDetail(detailIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('attendance/attendance-detail/export', {
        ...this.queryParams
      }, `attendance-detail_${new Date().getTime()}.xlsx`)
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
