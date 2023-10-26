<script lang="ts" setup>
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting, ArrowLeft, Search, User, House, Refresh, Plus, Edit, Delete,
} from '@element-plus/icons-vue'
import {reactive, ref} from 'vue'
import {ElTable} from 'element-plus'

const input = ref('')
const state = reactive({
  tableData: [
    {
      date: '2016-05-03',
      name: 'Tom',
      address: 'No. 189, Grove St, Los Angeles',
    },
  ]
})


const currentPage = ref(1)
const pageSize = ref(100)

const handleSizeChange = (val: number) => {
  console.log(`${val} items per page`)
}
const handleCurrentChange = (val: number) => {
  console.log(`current page: ${val}`)
}

const handleClick = () => {
  console.log('click')
}

</script>

<template>
  <div class="main-layout">
    <!-- 工具布局 -->
    <div class="tools-layout">
      <el-input
          v-model="input"
          class="w300"
          placeholder="请输入"
          style="margin-right: 10px"
      />
      <el-button type="primary" :icon="Search">搜索</el-button>
      <el-button type="warning" :icon="Refresh">重置</el-button>
      <el-button type="success" :icon="Plus">新增</el-button>
    </div>
    <!-- 数据表 -->
    <div class="data-layout">
      <el-table
          ref="multipleTableRef"
          :data="state.tableData"
          stripe
          max-height="500"
          style="width: 100%; margin-top: 10px"
      >

        <el-table-column type="selection" width="55"/>

        <el-table-column label="日期" width="120">
          <template #default="scope">{{ scope.row.date }}</template>
        </el-table-column>

        <el-table-column property="name" label="名称" width="120"/>

        <el-table-column property="address" label="地址" show-overflow-tooltip/>

        <el-table-column fixed="right" label="操作" width="120">
          <template #default>
            <el-button type="primary" :icon="Edit" circle plain/>
            <el-button type="danger" :icon="Delete" @click="handleClick" circle plain/>
          </template>
        </el-table-column>

      </el-table>
    </div>
    <div class="pagination-layout">
      <el-pagination
          v-model:current-page="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[100, 200, 300, 400]"
          :disabled="false"
          :background="true"
          layout="total, sizes, prev, pager, next, jumper"
          :total="400"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />
    </div>
  </div>
</template>

<style>

el-header {
  padding: 0;
  margin: 0;
}

th {
  background-color: #ecf5ff !important;
  font-weight: bold !important;
  font-size: larger;
}


.w300 {
  width: 300px;
}
</style>