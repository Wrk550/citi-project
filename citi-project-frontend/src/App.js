import React, { Fragment } from 'react'
import Login from './pages/login'
import Home from './pages/home'
import Enter from './pages/enter'
import Register from './pages/register'
import Client from './pages/client'
import Product from './pages/product'
import Stock from './pages/stock'
import { HashRouter, Route, Routes} from 'react-router-dom'

function App() {
  return (
    <Fragment>
      <HashRouter>
        <Routes>
          <Route path="/login" element={<Login/>} />
          <Route path="/home" element={<Home/>} />
          <Route path="/register" element={<Register/>} />
          <Route path="/enter" element={<Enter/>} />
          <Route path="/client" element={<Client/>} />
          <Route path="/product" element={<Product/>} />
          <Route path="/stock" element={<Stock/>} />
          <Route exact path="/" element={<Enter/>} />
        </Routes>
      </HashRouter>
    </Fragment>
  )
}


export default App
