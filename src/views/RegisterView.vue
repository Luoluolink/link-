<template>
  <div class="register-container">
      <!-- 顶部导航 -->
     <header class="nav-register">
      <nav class="nav-register">
       <p>B小町</p>
      </nav>
     </header>
        <div class="register-ne">
        <h2>用户注册</h2>
        <input v-model="user.username" placeholder="用户名"/>
        <input v-model="user.password" type="password" placeholder="密码"/>
        <input v-model="user.phone" type="phone" placeholder="手机号"/>
        <button @click="register">注册</button>
        <p v-if="errorMsg">{{ errorMsg }}</p>
        </div>
    </div>
      <!-- 底部信息 -->
    <footer class="footer">
      <p>@2025 B小町展示项目</p>
      <p>@LUOLUOLINK</p>
    </footer>
    <div class="info-section">
      <div class="info-box1">
        <h3>B小町偶像团体</h3>
        <p>来自传奇偶像团体B小町，致力于传递偶像精神</p>
      </div>
      <div class="info-box2">
        <h3>🎤 成员介绍</h3>
        <p>露比 | 有马加奈 | MEM啾</p>
      </div>
      <div class="info-box3">
        <h3>📀 代表作品</h3>
        <p>《STAR☆T☆RAIN》 | 《HEART’s♡KISS》 | 《深海52Hz》</p>
      </div>
      <div class="info-box4">
        <h3>🎉 粉丝文化</h3>
        <p>应援活动 | 线下见面会 | 周边商品</p>
      </div>
      <div class="info-box5">
        <h3>📢 关注我们</h3>
        <p>微博 | Twitter | YouTube</p>
      </div>
    </div>
</template>
<script>
export default{
    data(){
        return{
            user:{
                username:'',
                password:'',
                phone:''
            },
            errorMsg:''
        };
    },
    methods:{
        async register(){
            try{
                const response=await fetch('http://localhost:8080/api/register',{
                    method:'POST',
                    headers:{'Content-Type':'application/json'},
                    body:JSON.stringify(this.user)
                });
                const result = await response.text();
                if(response.ok){
                    alert('✅ ' + result);
                    this.$router.push('/login');
                }else{
                    this.errorMsg = result;
                }
            }catch(error){
                this.errorMsg = "❌ 服务器错误，请稍后再试！";
            }
        }
    }
};
</script>
<style scoped>
/* 页面背景 */
.register-container {
  width: 100%;
  margin: auto;
  min-height: 85vh;
  text-align: center;
  background-image: url('login2 (1).jpg');
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
}

/* 顶部导航 */
.nav-register {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  padding: 10px 40px;
 background: white;
  font-weight: bold;
  font-size: 18px;
  color: #ff6b81;
  position: relative;
  z-index: 100;
}

.nav-register p {
  font-size: 22px;
  font-weight: bold;
  letter-spacing: 1px;
}

.nav-register a {
  color: #ff6b81;
  text-decoration: none;
  font-size: 18px;
  transition: transform 0.5s ease, color 0.5s ease;
  margin: 0 15px;
}

.nav-register a:hover {
  color: rgb(242, 179, 221);
}

/* 登录框 */
.register-ne {
  position: absolute;
  top: 50%;
  right: 100px;
  transform: translateY(-65%);
  background: rgba(255, 255, 255, 0.6);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 400px;
  height: 300px;
  padding: 30px;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.3);
  border-radius: 20px;
  transition: all 0.3s ease-in-out;
}

input {
  padding: 12px;
  margin-bottom: 15px;
  width: 100%;
  border: 2px solid #ddd;
  border-radius: 5px;
}

button {
  background: #ff6b81;
  color: wheat;
  width: 300px;
  padding: 12px;
  border-radius: 10px;
  font-size: 20px;
  cursor: pointer;
  transition: background 0.3s;
}

button:hover {
  background: #e05269;
}

/* 底部说明 */
.info-section {
    position: relative; /* 确保它不会被绝对定位的元素遮挡 */
    z-index: 10; /* 让它高于某些背景元素，但低于导航 */
    display: flex;
    justify-content: center;
    gap: 30px;
    padding: 40px;
    padding-bottom: 90px;
    background: white;
    text-align: center;
    font-size: 14px;
    color: #333;
  
}
.info-box1,.info-box2,.info-box3,.info-box4,.info-box5{
    max-width: 300px;
    min-width: 250px;
    text-align: center;
}



.info-box1 h3,.info-box2 h3,.info-box3 h3,.info-box4 h3,.info-box5 h3{
  font-size: 18px;
  color: #ff6b81;
}
.footer{
position: fixed;
bottom:0;
left:0;
width: 100%;
background: rgba(0,0,0,0.3);
text-align: center;
padding:20px;
color:#d3d3d3;
z-index: 100;
}
</style>