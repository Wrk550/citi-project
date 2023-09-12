import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './register.styl'
const Register = () => {
  const [formData, setFormData] = useState({
    username: '',
    password: '',
    confirmPassword: ''
  });
  const [validationErrors, setValidationErrors] = useState({});

  const handleInputChange = (event) => {
    setFormData({
      ...formData,
      [event.target.name]: event.target.value
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    // 进行表单验证
    const errors = {};

    if (formData.username.trim() === '') {
      errors.username = 'Username is required.';
    }else if (formData.password.length < 6) {
      errors.password = 'Username must be at least 6 characters long.';
    }

    if (formData.password.trim() === '') {
      errors.password = 'Password is required.';
    } else if (formData.password.length < 6) {
      errors.password = 'Password must be at least 6 characters long.';
    }

    if (formData.confirmPassword.trim() === '') {
      errors.confirmPassword = 'Confirm Password is required.';
    } else if (formData.password !== formData.confirmPassword) {
      errors.confirmPassword = 'Passwords do not match.';
    }

    if (Object.keys(errors).length > 0) {
      setValidationErrors(errors);
      return;
    }
    

    // 发送POST请求到服务器
    fetch('/client/add', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(formData)
    })
      .then(response => response.json())
      .then(data => {
        // 处理服务器响应
        console.log(data);
        // 跳转到登录页面
         window.location.href = '/';
      })
      .catch(error => {
        // 处理错误
        console.error(error);
      });
  };

  return (
    <div className="register-container">
      <h2>Register</h2>
      <form className="register-form" onSubmit={handleSubmit}>
        <div className="register-form-group">
          <label htmlFor="username">用户</label>
          <input className='register-input'
            type="text"
            placeholder="不少于6个字符"
            name="username"
            value={formData.username}
            onChange={handleInputChange}
          />
          {validationErrors.username && <p>{validationErrors.username}</p>}
        </div>
      
        <div className="register-form-group">
          <label htmlFor="password">密码</label>
          <input className='register-input'
            type="password"
            name="password"
            placeholder="不少于6个字符"
            value={formData.password}
            onChange={handleInputChange}
          />
          {validationErrors.password && <p>{validationErrors.password}</p>}
        </div>
        <div className="register-form-group">
          <label htmlFor="confirm-password">确认密码</label>
          <input className='register-input'
            type="password"
            placeholder="与密码保持一致"
            name="confirmPassword"
            value={formData.confirmPassword}
            onChange={handleInputChange}
          />
          {validationErrors.confirmPassword && <p>{validationErrors.confirmPassword}</p>}
        </div>
        <button className='register-btn' type="submit">注册</button>
      </form>
    </div>
  );
};

export default Register;