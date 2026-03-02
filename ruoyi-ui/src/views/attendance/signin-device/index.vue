<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="设备唯一标识" prop="deviceCode">
        <el-input
          v-model="queryParams.deviceCode"
          placeholder="请输入设备唯一标识"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备名称" prop="deviceName">
        <el-input
          v-model="queryParams.deviceName"
          placeholder="请输入设备名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备类型" prop="deviceType">
        <el-select v-model="queryParams.deviceType" placeholder="请选择设备类型" clearable>
          <el-option label="人脸识别设备" :value="1" />
          <el-option label="指纹识别设备" :value="2" />
          <el-option label="二维码设备" :value="3" />
          <el-option label="位置签到设备" :value="4" />
          <el-option label="手机设备" :value="5" />
        </el-select>
      </el-form-item>
      <el-form-item label="设备IP地址" prop="deviceIp">
        <el-input
          v-model="queryParams.deviceIp"
          placeholder="请输入设备IP地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备MAC地址" prop="deviceMac">
        <el-input
          v-model="queryParams.deviceMac"
          placeholder="请输入设备MAC地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备物理位置" prop="deviceAddr">
        <el-input
          v-model="queryParams.deviceAddr"
          placeholder="请输入设备物理位置"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="绑定用户ID" prop="bindUserId">
        <el-input
          v-model="queryParams.bindUserId"
          placeholder="请输入绑定用户ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备状态" prop="deviceStatus">
        <el-select v-model="queryParams.deviceStatus" placeholder="请选择设备状态" clearable>
          <el-option label="正常" :value="1" />
          <el-option label="停用" :value="0" />
          <el-option label="维修中" :value="2" />
        </el-select>
      </el-form-item>
      <el-form-item label="在线状态" prop="onlineStatus">
        <el-select v-model="queryParams.onlineStatus" placeholder="请选择在线状态" clearable>
          <el-option label="在线" :value="1" />
          <el-option label="离线" :value="0" />
        </el-select>
      </el-form-item>
      <el-form-item label="最后在线时间" prop="lastOnlineTime">
        <el-date-picker clearable
                        v-model="queryParams.lastOnlineTime"
                        type="datetime"
                        value-format="yyyy-MM-dd HH:mm:ss"
                        placeholder="请选择最后在线时间">
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
          v-hasPermi="['attendance:signin-device:add']"
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
          v-hasPermi="['attendance:signin-device:edit']"
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
          v-hasPermi="['attendance:signin-device:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['attendance:signin-device:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="signinDeviceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="设备记录ID" align="center" prop="deviceId" width="80" />
      <el-table-column label="设备唯一标识" align="center" prop="deviceCode" width="120" show-overflow-tooltip />
      <el-table-column label="设备名称" align="center" prop="deviceName" min-width="120" show-overflow-tooltip />
      <el-table-column label="设备类型" align="center" prop="deviceType" width="100">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.deviceType === 1" type="primary">人脸识别设备</el-tag>
          <el-tag v-else-if="scope.row.deviceType === 2" type="success">指纹识别设备</el-tag>
          <el-tag v-else-if="scope.row.deviceType === 3" type="warning">二维码设备</el-tag>
          <el-tag v-else-if="scope.row.deviceType === 4" type="info">位置签到设备</el-tag>
          <el-tag v-else-if="scope.row.deviceType === 5" type="default">手机设备</el-tag>
          <span v-else>{{ scope.row.deviceType }}</span>
        </template>
      </el-table-column>
      <el-table-column label="设备IP地址" align="center" prop="deviceIp" width="120" />
      <el-table-column label="设备MAC地址" align="center" prop="deviceMac" width="120" />
      <el-table-column label="设备物理位置" align="center" prop="deviceAddr" min-width="120" show-overflow-tooltip />
      <el-table-column label="绑定用户ID" align="center" prop="bindUserId" width="100" />
      <el-table-column label="设备状态" align="center" prop="deviceStatus" width="80">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.deviceStatus === 1" type="success">正常</el-tag>
          <el-tag v-else-if="scope.row.deviceStatus === 0" type="danger">停用</el-tag>
          <el-tag v-else-if="scope.row.deviceStatus === 2" type="warning">维修中</el-tag>
          <span v-else>{{ scope.row.deviceStatus }}</span>
        </template>
      </el-table-column>
      <el-table-column label="在线状态" align="center" prop="onlineStatus" width="80">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.onlineStatus === 1" type="success">在线</el-tag>
          <el-tag v-else-if="scope.row.onlineStatus === 0" type="danger">离线</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="最后在线时间" align="center" prop="lastOnlineTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.lastOnlineTime, '{y}-{m}-{d} {h}:{i}') }}</span>
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
            v-hasPermi="['attendance:signin-device:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['attendance:signin-device:remove']"
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

    <!-- 添加或修改签到设备对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="设备唯一标识" prop="deviceCode">
          <el-input v-model="form.deviceCode" placeholder="请输入设备唯一标识" />
        </el-form-item>
        <el-form-item label="设备名称" prop="deviceName">
          <el-input v-model="form.deviceName" placeholder="请输入设备名称" />
        </el-form-item>
        <el-form-item label="设备类型" prop="deviceType">
          <el-radio-group v-model="form.deviceType">
            <el-radio :label="1">人脸识别设备</el-radio>
            <el-radio :label="2">指纹识别设备</el-radio>
            <el-radio :label="3">二维码设备</el-radio>
            <el-radio :label="4">位置签到设备</el-radio>
            <el-radio :label="5">手机设备</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="设备IP地址" prop="deviceIp">
          <el-input v-model="form.deviceIp" placeholder="请输入设备IP地址" />
        </el-form-item>
        <el-form-item label="设备MAC地址" prop="deviceMac">
          <el-input v-model="form.deviceMac" placeholder="请输入设备MAC地址" />
        </el-form-item>
        <el-form-item label="设备物理位置" prop="deviceAddr">
          <el-input v-model="form.deviceAddr" placeholder="请输入设备物理位置" />
        </el-form-item>
        <el-form-item label="绑定用户ID" prop="bindUserId">
          <el-input v-model="form.bindUserId" placeholder="请输入绑定用户ID" />
        </el-form-item>
        <el-form-item label="设备状态" prop="deviceStatus">
          <el-radio-group v-model="form.deviceStatus">
            <el-radio :label="1">正常</el-radio>
            <el-radio :label="0">停用</el-radio>
            <el-radio :label="2">维修中</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="在线状态" prop="onlineStatus">
          <el-radio-group v-model="form.onlineStatus">
            <el-radio :label="1">在线</el-radio>
            <el-radio :label="0">离线</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="最后在线时间" prop="lastOnlineTime">
          <el-date-picker clearable
                          v-model="form.lastOnlineTime"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="请选择最后在线时间"
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
import { listSigninDevice, getSigninDevice, delSigninDevice, addSigninDevice, updateSigninDevice } from "@/api/attendance/signin-device"

export default {
  name: "SigninDevice",
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
      // 签到设备表格数据
      signinDeviceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deviceCode: null,
        deviceName: null,
        deviceType: null,
        deviceIp: null,
        deviceMac: null,
        deviceAddr: null,
        bindUserId: null,
        deviceStatus: null,
        onlineStatus: null,
        lastOnlineTime: null,
        updateUserId: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        deviceCode: [
          { required: true, message: "设备唯一标识不能为空", trigger: "blur" },
          { min: 1, max: 32, message: "长度在 1 到 32 个字符", trigger: "blur" }
        ],
        deviceName: [
          { required: true, message: "设备名称不能为空", trigger: "blur" },
          { min: 1, max: 50, message: "长度在 1 到 50 个字符", trigger: "blur" }
        ],
        deviceType: [
          { required: true, message: "设备类型不能为空", trigger: "change" }
        ],
        deviceAddr: [
          { required: true, message: "设备物理位置不能为空", trigger: "blur" },
          { min: 1, max: 200, message: "长度在 1 到 200 个字符", trigger: "blur" }
        ],
        deviceStatus: [
          { required: true, message: "设备状态不能为空", trigger: "change" }
        ],
        onlineStatus: [
          { required: true, message: "在线状态不能为空", trigger: "change" }
        ],
        updateUserId: [
          { required: true, message: "更新用户不能为空", trigger: "blur" },
          { min: 1, max: 12, message: "长度在 1 到 12 个字符", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询签到设备列表 */
    getList() {
      this.loading = true
      listSigninDevice(this.queryParams).then(response => {
        this.signinDeviceList = response.rows
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
        deviceId: null,
        deviceCode: null,
        deviceName: null,
        deviceType: 1, // 默认人脸识别设备
        deviceIp: null,
        deviceMac: null,
        deviceAddr: null,
        bindUserId: null,
        deviceStatus: 1, // 默认正常
        onlineStatus: 1, // 默认在线
        lastOnlineTime: null,
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
      this.ids = selection.map(item => item.deviceId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加签到设备"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const deviceId = row.deviceId || this.ids[0]
      getSigninDevice(deviceId).then(response => {
        this.form = response.data
        // 确保数字字段类型正确
        if (this.form.deviceType) this.form.deviceType = Number(this.form.deviceType)
        if (this.form.deviceStatus) this.form.deviceStatus = Number(this.form.deviceStatus)
        if (this.form.onlineStatus) this.form.onlineStatus = Number(this.form.onlineStatus)
        if (this.form.isDeleted) this.form.isDeleted = Number(this.form.isDeleted)

        this.open = true
        this.title = "修改签到设备"
      }).catch(error => {
        console.error("获取设备信息失败:", error)
        this.$modal.msgError("获取设备信息失败")
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          // 数据类型转换
          const submitData = {
            ...this.form,
            deviceType: Number(this.form.deviceType),
            deviceStatus: Number(this.form.deviceStatus),
            onlineStatus: Number(this.form.onlineStatus),
            isDeleted: Number(this.form.isDeleted || 0)
          }

          if (submitData.deviceId != null) {
            updateSigninDevice(submitData).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            }).catch(error => {
              console.error("修改失败:", error)
              this.$modal.msgError("修改失败")
            })
          } else {
            addSigninDevice(submitData).then(response => {
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
      const deviceIds = row.deviceId || this.ids
      this.$modal.confirm('是否确认删除签到设备编号为"' + deviceIds + '"的数据项？').then(() => {
        return delSigninDevice(deviceIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('attendance/signin-device/export', {
        ...this.queryParams
      }, `signin-device_${new Date().getTime()}.xlsx`)
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
