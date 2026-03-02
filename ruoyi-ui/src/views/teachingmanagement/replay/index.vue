<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="关联教师课程ID" prop="teacherCourseId">
        <el-input
          v-model="queryParams.teacherCourseId"
          placeholder="请输入关联教师课程ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="课程所属学期" prop="semester">
        <el-input
          v-model="queryParams.semester"
          placeholder="请输入课程所属学期"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="回放标题" prop="replayTitle">
        <el-input
          v-model="queryParams.replayTitle"
          placeholder="请输入回放标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="视频存储URL" prop="videoUrl">
        <el-input
          v-model="queryParams.videoUrl"
          placeholder="请输入视频存储URL"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="视频时长" prop="videoDuration">
        <el-input
          v-model="queryParams.videoDuration"
          placeholder="请输入视频时长(秒)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="视频封面图URL" prop="coverUrl">
        <el-input
          v-model="queryParams.coverUrl"
          placeholder="请输入视频封面图URL"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="课程录制日期" prop="recordDate">
        <el-date-picker clearable
                        v-model="queryParams.recordDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择课程录制日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="课程录制时间" prop="recordTime">
        <el-date-picker clearable
                        v-model="queryParams.recordTime"
                        type="datetime"
                        value-format="yyyy-MM-dd HH:mm:ss"
                        placeholder="请选择课程录制开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="累计播放次数" prop="playCount">
        <el-input
          v-model="queryParams.playCount"
          placeholder="请输入累计播放次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option label="正常" :value="1" />
          <el-option label="停用" :value="0" />
        </el-select>
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
          v-hasPermi="['teachingmanagement:replay:add']"
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
          v-hasPermi="['teachingmanagement:replay:edit']"
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
          v-hasPermi="['teachingmanagement:replay:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['teachingmanagement:replay:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="replayList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="回放记录ID" align="center" prop="replayId" width="80" />
      <el-table-column label="关联教师课程ID" align="center" prop="teacherCourseId" width="100" />
      <el-table-column label="课程所属学期" align="center" prop="semester" width="120" />
      <el-table-column label="回放标题" align="center" prop="replayTitle" min-width="150" show-overflow-tooltip />
      <el-table-column label="视频存储URL" align="center" prop="videoUrl" min-width="120" show-overflow-tooltip />
      <el-table-column label="视频时长(秒)" align="center" prop="videoDuration" width="100" />
      <el-table-column label="视频封面图URL" align="center" prop="coverUrl" min-width="120" show-overflow-tooltip />
      <el-table-column label="课程录制日期" align="center" prop="recordDate" width="120">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.recordDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="课程录制时间" align="center" prop="recordTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.recordTime, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="累计播放次数" align="center" prop="playCount" width="100" />
      <el-table-column label="状态" align="center" prop="status" width="80">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status === 1" type="success">正常</el-tag>
          <el-tag v-else-if="scope.row.status === 0" type="danger">停用</el-tag>
          <span v-else>{{ scope.row.status }}</span>
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
            v-hasPermi="['teachingmanagement:replay:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['teachingmanagement:replay:remove']"
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

    <!-- 添加或修改课程回放对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="关联教师课程ID" prop="teacherCourseId">
          <el-input v-model="form.teacherCourseId" placeholder="请输入关联教师课程ID" type="number" />
        </el-form-item>
        <el-form-item label="课程所属学期" prop="semester">
          <el-input v-model="form.semester" placeholder="请输入课程所属学期" />
        </el-form-item>
        <el-form-item label="回放标题" prop="replayTitle">
          <el-input v-model="form.replayTitle" placeholder="请输入回放标题" />
        </el-form-item>
        <el-form-item label="视频存储URL" prop="videoUrl">
          <el-input v-model="form.videoUrl" placeholder="请输入视频存储URL" />
        </el-form-item>
        <el-form-item label="视频时长(秒)" prop="videoDuration">
          <el-input v-model="form.videoDuration" placeholder="请输入视频时长(秒)" type="number" min="0" />
        </el-form-item>
        <el-form-item label="视频封面图URL" prop="coverUrl">
          <el-input v-model="form.coverUrl" placeholder="请输入视频封面图URL" />
        </el-form-item>
        <el-form-item label="课程录制日期" prop="recordDate">
          <el-date-picker clearable
                          v-model="form.recordDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择课程录制日期"
                          style="width: 100%">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="课程录制时间" prop="recordTime">
          <el-date-picker clearable
                          v-model="form.recordTime"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="请选择课程录制开始时间"
                          style="width: 100%">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="累计播放次数" prop="playCount">
          <el-input v-model="form.playCount" placeholder="请输入累计播放次数" type="number" min="0" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio :label="1">正常</el-radio>
            <el-radio :label="0">停用</el-radio>
          </el-radio-group>
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
import { listReplay, getReplay, delReplay, addReplay, updateReplay } from "@/api/teachingmanagement/replay"

export default {
  name: "Replay",
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
      if (Number(value) < 0) {
        callback(new Error('数值不能为负数'));
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
      // 课程回放表格数据
      replayList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        teacherCourseId: null,
        semester: null,
        replayTitle: null,
        videoUrl: null,
        videoDuration: null,
        coverUrl: null,
        recordDate: null,
        recordTime: null,
        playCount: null,
        status: null,
        updateUserId: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        teacherCourseId: [
          { required: true, message: "关联教师课程ID不能为空", trigger: "blur" },
          { validator: validateNumber, trigger: "blur" }
        ],
        semester: [
          { required: true, message: "课程所属学期不能为空", trigger: "blur" },
          { min: 1, max: 20, message: "长度在 1 到 20 个字符", trigger: "blur" }
        ],
        replayTitle: [
          { required: true, message: "回放标题不能为空", trigger: "blur" },
          { min: 1, max: 200, message: "长度在 1 到 200 个字符", trigger: "blur" }
        ],
        videoUrl: [
          { required: true, message: "视频存储URL不能为空", trigger: "blur" },
          { min: 1, max: 255, message: "长度在 1 到 255 个字符", trigger: "blur" }
        ],
        videoDuration: [
          { required: true, message: "视频时长不能为空", trigger: "blur" },
          { validator: validateInteger, trigger: "blur" }
        ],
        coverUrl: [
          { max: 255, message: "长度不能超过 255 个字符", trigger: "blur" }
        ],
        recordDate: [
          { required: true, message: "课程录制日期不能为空", trigger: "change" }
        ],
        recordTime: [
          { required: true, message: "课程录制时间不能为空", trigger: "change" }
        ],
        playCount: [
          { required: true, message: "累计播放次数不能为空", trigger: "blur" },
          { validator: validateInteger, trigger: "blur" }
        ],
        status: [
          { required: true, message: "状态不能为空", trigger: "change" }
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
    /** 查询课程回放列表 */
    getList() {
      this.loading = true
      listReplay(this.queryParams).then(response => {
        this.replayList = response.rows
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
        replayId: null,
        teacherCourseId: null,
        semester: null,
        replayTitle: null,
        videoUrl: null,
        videoDuration: null,
        coverUrl: null,
        recordDate: null,
        recordTime: null,
        playCount: null,
        status: 1, // 默认正常状态
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
      this.ids = selection.map(item => item.replayId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加课程回放"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const replayId = row.replayId || this.ids[0]
      getReplay(replayId).then(response => {
        this.form = response.data
        // 确保数字字段类型正确
        if (this.form.status) this.form.status = Number(this.form.status)
        if (this.form.isDeleted) this.form.isDeleted = Number(this.form.isDeleted)
        if (this.form.teacherCourseId) this.form.teacherCourseId = Number(this.form.teacherCourseId)
        if (this.form.videoDuration) this.form.videoDuration = Number(this.form.videoDuration)
        if (this.form.playCount) this.form.playCount = Number(this.form.playCount)
        if (this.form.updateUserId) this.form.updateUserId = Number(this.form.updateUserId)

        this.open = true
        this.title = "修改课程回放"
      }).catch(error => {
        console.error("获取回放信息失败:", error)
        this.$modal.msgError("获取回放信息失败")
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          // 数据类型转换
          const submitData = {
            ...this.form,
            status: Number(this.form.status),
            isDeleted: Number(this.form.isDeleted || 0)
          }

          // 转换ID字段为数字
          if (this.form.teacherCourseId) submitData.teacherCourseId = Number(this.form.teacherCourseId)
          if (this.form.updateUserId) submitData.updateUserId = Number(this.form.updateUserId)
          if (this.form.videoDuration) submitData.videoDuration = Number(this.form.videoDuration)
          if (this.form.playCount) submitData.playCount = Number(this.form.playCount)

          if (submitData.replayId != null) {
            updateReplay(submitData).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            }).catch(error => {
              console.error("修改失败:", error)
              this.$modal.msgError("修改失败")
            })
          } else {
            addReplay(submitData).then(response => {
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
      const replayIds = row.replayId || this.ids
      this.$modal.confirm('是否确认删除课程回放编号为"' + replayIds + '"的数据项？').then(() => {
        return delReplay(replayIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('teachingmanagement/replay/export', {
        ...this.queryParams
      }, `replay_${new Date().getTime()}.xlsx`)
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
