import React, { useState, useEffect, Fragment } from 'react';
import {Link} from 'react-router-dom';
import './client.styl'
function Client() {
  const [clients, setClients] = useState([]);
  const [clientName, setclientName] = useState('');
  const [phoneNumber, setphoneNumber] = useState('');
  
  useEffect(() => {
    fetchClients();
  }, []);
  
  //获取客户信息表
  const fetchClients = async () => {
    try {
      const response = await fetch('/info/client/listInfo',
      {
        Accept: 'application/json'
      });

      const data = await response.json();
      // setClients(data);
    } catch (error) {
      console.log(error);
    }
  };

  //添加客户
  const handleAddClient = async (e) => {
    e.preventDefault();
    setclientName('');
    setphoneNumber('');
    //表格显示添加客户
    setClients([...clients,clientName]);
    try {
      const response = await fetch('http://114.132.91.6:9000/client/info/client/add', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ clientName,phoneNumber }),
      });
      const data = await response.json();
      // setClients([...clients, data]);
      setclientName('');
      setphoneNumber('');
    } catch (error) {
      console.log(error);
    }
  };

  //删除客户
  const handleDeleteClient = async (ids) => {
    try {
      await fetch(`/info/client/delete/${ids}`, {
        method: 'POST',
      });
      const updatedClients = clients.filter((client) => client.id !== ids);
      setClients(updatedClients);
    } catch (error) {
      console.log(error);
    }
  };




    return(
<Fragment>
<div className="client-container">
<form onSubmit={handleAddClient}>
        <input type="text" value={clientName} onChange={(e) => setclientName(e.target.value)} placeholder="clientName" />
        <input type="text" value={phoneNumber} onChange={(e) => setphoneNumber(e.target.value)} placeholder="phoneNumber" />
        <button className='add-btn' type="submit">Add Client</button>
      </form>
<h2>Clients Table</h2>
<div>
{clients.length > 0 && (
        <table>
          <thead>
            <tr>
            <th>Client id</th>
            <th>Client Name</th>
            <th>Client phoneNumber</th>
            <th>Action</th>
            </tr>
          </thead>
          <tbody>
          {clients.map((client) => (
            <tr key={client.id}>
              <td>{client.id}</td>
              <td>{client.clientName}</td>
              <td>{client.phoneNumber}</td>
              <td>
                <button  className='delete-btn' onClick={() => handleDeleteClient(client.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
        </table>
        )}
        </div>
    <div className='client-button'>
      <button className='client-btn'><Link to="/enter">返回</Link></button>
      <button className='client-btn'><Link to="/home">进行交易</Link></button>
  </div>
  </div>
</Fragment>
    );
};

export default Client;
