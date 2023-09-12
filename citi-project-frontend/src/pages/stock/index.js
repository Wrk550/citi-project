import React, { useState, useEffect, Fragment } from 'react';
import {Link} from 'react-router-dom';
import './stock.styl'
function Stock() {
  const [stocks, setstocks] = useState([]);
  const [description, setdescription] = useState('');
  const [issueSector, setissueSector] = useState('');
  const [name, setname] = useState('');
  const [price, setprice] = useState('');
  const [ric, setric] = useState('');
  const [ticker, setticker] = useState('');
 
  
  //分页查询股票信息
  const fetchProduct = async () => {
    try {
      const response = await fetch(`/info/product/list/
      ${issueSector, name,ric,ticker}`);
      const data = await response.json();
      // setstocks(data);
      setissueSector('');
      setname('');
      setric('');
      setticker('');
    } catch (error) {
      console.log(error);
    }
  };

  //添加股票
  const handleAddStock = async (e) => {
    e.preventDefault();
    setstocks([...stocks, description]);
    setdescription('');
      setissueSector('');
      setname('');
      setprice('');
      setric('');
      setticker('');
    try {
      const response = await fetch('/info/product/add', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ description,issueSector,name, 
                               price,ric,ticker
        }),
      });
      const data = await response.json();
      // setstocks([...stocks, data]);
      // setdescription('');
      // setissueSector('');
      // setname('');
      // setprice('');
      // setric('');
      // setticker('');
    } catch (error) {
      console.log(error);
    }
  };

  //删除股票
  const handleDeleteStock = async (ids) => {
    try {
      await fetch(`/info/product/delete/${ids}`, {
        method: 'POST',
      });
      const updatedStocks = stocks.filter((stock) => stock.id !== ids);
      setstocks(updatedStocks);
    } catch (error) {
      console.log(error);
    }
  };




    return(
<Fragment>
<div className="stock-container">
<form onSubmit={handleAddStock}>
        <input type="text" value={description} onChange={(e) => setdescription(e.target.value)} placeholder="description" />
        <input type="text" value={issueSector} onChange={(e) => setissueSector(e.target.value)} placeholder="issueSector" />
        <input type="text" value={name} onChange={(e) => setname(e.target.value)} placeholder="name" />
        <input type="text" value={price} onChange={(e) => setprice(e.target.value)} placeholder="price" />
        <input type="text" value={ric} onChange={(e) => setric(e.target.value)} placeholder="ric" />
        <input type="text" value={ticker} onChange={(e) => setticker(e.target.value)} placeholder="ticker" />
        <button className='add-btn' type="submit">Add Client</button>
        
      </form>
      <button className='check-btn' type="submit" onClick={fetchProduct}>Search</button>
<h2>Stocks Table</h2>
<div>
{stocks.length > 0 && (
        <table>
          <thead>
            <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Ticker</th>
            <th>Description</th>
            <th>CreatedAt</th>
            <th>IssueSector</th>
            <th>Price</th>
            <th>Ric</th>
            <th>Stock</th>
            <th>Action</th>
            </tr>
          </thead>
          <tbody>
          {stocks.map((stock) => (
            <tr key={stock.id}>
              <td>{stock.id}</td>
              <td>{stock.name}</td>
              <td>{stock.ticker}</td>
              <td>{stock.description}</td>
              <td>{stock.createdAt}</td>
              <td>{stock.issueSector}</td>
              <td>{stock.price}</td>
              <td>{stock.ric}</td>
              <td>{stock.stock}</td>
              <td>
                <button  className='delete-btn' onClick={() => handleDeleteStock(stock.id)}>Delete</button>
              </td>
            </tr>

          ))}
        </tbody>
        </table>
        )}
        </div>
    <div className='stock-button'>
      <button className='stock-btn'><Link to="/enter">返回</Link></button>
      <button className='stock-btn'><Link to="/home">进行交易</Link></button>
  </div>
  </div>
</Fragment>
    );
};

export default Stock;
