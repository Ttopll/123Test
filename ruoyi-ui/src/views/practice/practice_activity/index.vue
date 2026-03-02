<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="活动名称" prop="activityName">
        <el-input
          v-model="queryParams.activityName"
          placeholder="请输入活动名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="主办方" prop="organizer">
        <el-input
          v-model="queryParams.organizer"
          placeholder="请输入主办方"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="协办方" prop="coOrganizer">
        <el-input
          v-model="queryParams.coOrganizer"
          placeholder="请输入协办方"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动开始时间" prop="activityTime">
        <el-date-picker clearable
          v-model="queryParams.activityTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择活动开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="活动结束时间" prop="activityEndTime">
        <el-date-picker clearable
          v-model="queryParams.activityEndTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择活动结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="报名开始时间" prop="signStartTime">
        <el-date-picker clearable
          v-model="queryParams.signStartTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择报名开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="报名结束时间" prop="signEndTime">
        <el-date-picker clearable
          v-model="queryParams.signEndTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择报名结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="活动地点" prop="activityLocation">
        <el-input
          v-model="queryParams.activityLocation"
          placeholder="请输入活动地点"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最大参与人数" prop="maxQuota">
        <el-input
          v-model="queryParams.maxQuota"
          placeholder="请输入最大参与人数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="面向人群" prop="targetGroup">
        <el-input
          v-model="queryParams.targetGroup"
          placeholder="请输入面向人群"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建人 ID" prop="createUserId">
        <el-input
          v-model="queryParams.createUserId"
          placeholder="请输入创建人 ID"
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
          v-hasPermi="['practice:practice_activity:add']"
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
          v-hasPermi="['practice:practice_activity:edit']"
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
          v-hasPermi="['practice:practice_activity:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['practice:practice_activity:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="practice_activityList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="活动 ID" align="center" prop="activityId" />
      <el-table-column label="活动名称" align="center" prop="activityName" />
      <el-table-column label="活动类型" align="center" prop="activityType" />
      <el-table-column label="主办方" align="center" prop="organizer" />
      <el-table-column label="协办方" align="center" prop="coOrganizer" />
      <el-table-column label="活动开始时间" align="center" prop="activityTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.activityTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="活动结束时间" align="center" prop="activityEndTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.activityEndTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="报名开始时间" align="center" prop="signStartTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.signStartTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="报名结束时间" align="center" prop="signEndTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.signEndTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="活动地点" align="center" prop="activityLocation" />
      <el-table-column label="最大参与人数" align="center" prop="maxQuota" />
      <el-table-column label="面向人群" align="center" prop="targetGroup" />
      <el-table-column label="活动详情" align="center" prop="activityDesc" />
      <el-table-column label="所需材料" align="center" prop="requiredMaterial" />
      <el-table-column label="活动状态" align="center" prop="activityStatus" />
      <el-table-column label="创建人 ID" align="center" prop="createUserId" />
      <el-table-column label="更新用户" align="center" prop="updateUserId" />
      <el-table-column label="删除状态" align="center" prop="isDeleted" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['practice:practice_activity:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['practice:practice_activity:remove']"
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

    <!-- 添加或修改实践活动对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="活动名称" prop="activityName">
          <el-input v-model="form.activityName" placeholder="请输入活动名称" />
        </el-form-item>
        <el-form-item label="主办方" prop="organizer">
          <el-input v-model="form.organizer" placeholder="请输入主办方" />
        </el-form-item>
        <el-form-item label="协办方" prop="coOrganizer">
          <el-input v-model="form.coOrganizer" placeholder="请输入协办方" />
        </el-form-item>
        <el-form-item label="活动开始时间" prop="activityTime">
          <el-date-picker clearable
            v-model="form.activityTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择活动开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="活动结束时间" prop="activityEndTime">
          <el-date-picker clearable
            v-model="form.activityEndTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择活动结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="报名开始时间" prop="signStartTime">
          <el-date-picker clearable
            v-model="form.signStartTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择报名开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="报名结束时间" prop="signEndTime">
          <el-date-picker clearable
            v-model="form.signEndTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择报名结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="活动地点" prop="activityLocation">
          <el-input v-model="form.activityLocation" placeholder="请输入活动地点" />
        </el-form-item>
        <el-form-item label="最大参与人数" prop="maxQuota">
          <el-input v-model="form.maxQuota" placeholder="请输入最大参与人数" />
        </el-form-item>
        <el-form-item label="面向人群" prop="targetGroup">
          <el-input v-model="form.targetGroup" placeholder="请输入面向人群" />
        </el-form-item>
        <el-form-item label="活动详情" prop="activityDesc">
          <el-input v-model="form.activityDesc" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="所需材料" prop="requiredMaterial">
          <el-input v-model="form.requiredMaterial" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="创建人 ID" prop="createUserId">
          <el-input v-model="form.createUserId" placeholder="请输入创建人 ID" />
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
import { listPractice_activity, getPractice_activity, delPractice_activity, addPractice_activity, updatePractice_activity } from "@/api/practice/practice_activity"

export default {
  name: "Practice_activity",
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
      // 实践活动表格数据
      practice_activityList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        activityName: null,
        activityType: null,
        organizer: null,
        coOrganizer: null,
        activityTime: null,
        activityEndTime: null,
        signStartTime: null,
        signEndTime: null,
        activityLocation: null,
        maxQuota: null,
        targetGroup: null,
        activityDesc: null,
        requiredMaterial: null,
        activityStatus: null,
        createUserId: null,
        updateUserId: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        activityName: [
          { required: true, message: "活动名称不能为空", trigger: "blur" }
        ],
        activityType: [
          { required: true, message: "活动类型不能为空", trigger: "change" }
        ],
        organizer: [
          { required: true, message: "主办方不能为空", trigger: "blur" }
        ],
        activityTime: [
          { required: true, message: "活动开始时间不能为空", trigger: "blur" }
        ],
        activityEndTime: [
          { required: true, message: "活动结束时间不能为空", trigger: "blur" }
        ],
        signStartTime: [
          { required: true, message: "报名开始时间不能为空", trigger: "blur" }
        ],
        signEndTime: [
          { required: true, message: "报名结束时间不能为空", trigger: "blur" }
        ],
        activityLocation: [
          { required: true, message: "活动地点不能为空", trigger: "blur" }
        ],
        maxQuota: [
          { required: true, message: "最大参与人数不能为空", trigger: "blur" }
        ],
        targetGroup: [
          { required: true, message: "面向人群不能为空", trigger: "blur" }
        ],
        activityDesc: [
          { required: true, message: "活动详情不能为空", trigger: "blur" }
        ],
        activityStatus: [
          { required: true, message: "活动状态不能为空", trigger: "change" }
        ],
        createUserId: [
          { required: true, message: "创建人 ID不能为空", trigger: "blur" }
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
    /** 查询实践活动列表 */
    getList() {
      this.loading = true
      listPractice_activity(this.queryParams).then(response => {
        this.practice_activityList = response.rows
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
        activityId: null,
        activityName: null,
        activityType: null,
        organizer: null,
        coOrganizer: null,
        activityTime: null,
        activityEndTime: null,
        signStartTime: null,
        signEndTime: null,
        activityLocation: null,
        maxQuota: null,
        targetGroup: null,
        activityDesc: null,
        requiredMaterial: null,
        activityStatus: null,
        createUserId: null,
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
      this.ids = selection.map(item => item.activityId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加实践活动"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const activityId = row.activityId || this.ids
      getPractice_activity(activityId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改实践活动"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.activityId != null) {
            updatePractice_activity(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addPractice_activity(this.form).then(response => {
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
      const activityIds = row.activityId || this.ids
      this.$modal.confirm('是否确认删除实践活动编号为"' + activityIds + '"的数据项？').then(function() {
        return delPractice_activity(activityIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('practice/practice_activity/export', {
        ...this.queryParams
      }, `practice_activity_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
