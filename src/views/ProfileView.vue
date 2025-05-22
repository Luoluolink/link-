<script setup lang="ts">
import { ref, onMounted } from "vue";
import axios, { AxiosError } from "axios";

const username = ref(localStorage.getItem("username") || "");
const userInfo = ref({
  avatar: "",
  username: "",
  phone: "",
  password: "",
});

const isEditingPhone = ref(false);
const isEditingPassword = ref(false);

const fetchUserData = async () => {
  if (!username.value) return;
  try {
    const response = await axios.get(`http://localhost:8080/api/my/${username.value}`);
    if (response.status === 200) {
      userInfo.value = response.data;
    }
  } catch (error) {
    console.error("❌ 获取用户数据失败:", (error as AxiosError).message);
  }
};

const uploadAvatar = async (event: Event) => {
  const file = (event.target as HTMLInputElement).files?.[0];
  if (!file) return;

  const formData = new FormData();
  formData.append("file", file);

  try {
    const response = await axios.post(`http://localhost:8080/api/upload/avatar/${username.value}`, formData, {
      headers: { "Content-Type": "multipart/form-data" },
    });

    userInfo.value.avatar = response.data;
    await fetchUserData();
  } catch (error) {
    console.error("❌ 头像上传失败:", error);
  }
};

const signature = ref(localStorage.getItem("signature") || "这个用户很神秘，还没有签名...");
const isEditingSignature = ref(false);

const saveSignature = () => {
  localStorage.setItem("signature", signature.value);
  isEditingSignature.value = false;
};

onMounted(() => {
  signature.value = localStorage.getItem("signature") || "这个用户很神秘，还没有签名...";
});
const updateUserData = async () => {
  if (!username.value) return;

  try {
    await axios.put(`http://localhost:8080/api/my/${username.value}`, {
      phone: userInfo.value.phone,
      password: userInfo.value.password,
    });

    alert("✅ 用户信息更新成功！");
    isEditingPhone.value = false;
    isEditingPassword.value = false;
    fetchUserData();
  } catch (error) {
    console.error("❌ 更新失败:", (error as AxiosError).message);
  }
};

onMounted(fetchUserData);
</script>

<template>
  <div class="profile-wrapper">
    <div class="profile-header">
      
      <h2>个人信息</h2>
    </div>

    <div class="profile-content">
      <div class="avatar-section">
        <label for="fileInput">
          <img :src="userInfo.avatar || '/default-avatar.png'" alt="头像" class="avatar-image" />
        </label>
        <input type="file" id="fileInput" @change="uploadAvatar" accept="image/*" class="hidden-input" />
      </div>

      <div class="info-section">
        <div class="info-item"><strong>用户名:</strong> {{ userInfo.username || "未设置" }}</div>

        <div class="info-item">
          <strong>密码:</strong> {{ userInfo.password || "未设置" }}
          <button @click="isEditingPassword = true" class="edit-button">修改</button>
          <input v-if="isEditingPassword" v-model="userInfo.password" @blur="isEditingPassword = false" class="input-field"/>
        </div>

        <div class="info-item">
          <strong>手机号:</strong> {{ userInfo.phone || "未填写" }}
          <button @click="isEditingPhone = true" class="edit-button">修改</button>
          <input v-if="isEditingPhone" v-model="userInfo.phone" @blur="isEditingPhone = false" class="input-field"/>
        </div>

        <button @click="updateUserData" class="save-button">保存修改</button>
      </div>
    </div>
  </div>
</template>


<style>
.profile-wrapper {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 30px;
  background: linear-gradient(to right, #ffffff, #f5f5f5);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  width: 450px; /* ✅ 调整宽度使其符合新设计 */
  margin: auto;
}

.profile-header h2 {
  color: #333;
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 20px;
}

.profile-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 20px;
}

.avatar-section {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.avatar-image {
  width: 90px;
  height: 90px;
  border-radius: 50%;
  border: 2px solid #ff80ab;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  cursor: pointer;
  transition: transform 0.3s ease;
}
.avatar-image:hover {
  transform: scale(1.1);
}

.info-section {
  display: flex;
  flex-direction: column;
  gap: 15px;
  font-size: 18px;
}

.info-item {
  display: flex;
  align-items: center;
  gap: 10px;
}

.edit-button, .save-button {
  padding: 8px 14px;
  background: #ff80ab;
  color: white;
  font-size: 14px;
  font-weight: bold;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition: background 0.3s ease, transform 0.3s ease;
}
.edit-button:hover, .save-button:hover {
  background: #ff4d7e;
  transform: scale(1.05);
}

.input-field {
  padding: 6px;
  font-size: 16px;
  border: 1px solid #ddd;
  border-radius: 6px;
}

.hidden-input {
  display: none;
}
.signature-section {
  display: flex;
  align-items: center;
  gap: 10px;
  font-size: 18px;
}

.signature-input {
  padding: 6px;
  font-size: 16px;
  border: 1px solid #ddd;
  border-radius: 6px;
  width: 200px;
}

.edit-button {
  padding: 8px 14px;
  background: #ff80ab;
  color: white;
  font-size: 14px;
  font-weight: bold;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition: background 0.3s ease, transform 0.3s ease;
}
.edit-button:hover {
  background: #ff4d7e;
  transform: scale(1.05);
}

</style>




