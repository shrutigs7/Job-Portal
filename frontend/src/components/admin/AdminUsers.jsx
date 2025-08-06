// // import React, { useEffect, useState } from 'react';
// // import axios from 'axios';
// // import UserDetailsCard from './UserDetailsCard';

// // function AdminUsers() {
// //   const [users, setUsers] = useState([]);
// //   const [selectedUserId, setSelectedUserId] = useState(null);
// //   const [userDetails, setUserDetails] = useState(null);
// //   const API_BASE_URL = 'http://localhost:8081';

// //   useEffect(() => {
// //     axios.get(`${API_BASE_URL}/admin/users`)
// //       .then(res => {
// //         setUsers(res.data);
// //         if (res.data.length > 0) {
// //           setSelectedUserId(res.data[0].id);
// //         }
// //         console.log(users)
// //       })
// //       .catch(err => console.error('Failed to fetch users', err));
// //   }, []);

// //   useEffect(() => {
// //     if (selectedUserId) {
// //       axios.get(`${API_BASE_URL}/admin/user-details/${selectedUserId}`)
// //         .then(res => setUserDetails(res.data))
// //         .catch(err => console.error('Failed to fetch user details', err));
// //     }
// //   }, [selectedUserId]);

// //   return (
// //     <div className="row">
// //       {/* User List */}
// //       <div className="col-md-5">
// //         <div className="list-group">
// //           {users.map(user => (
            
// //             <div
// //               key={user.id}
// //               className={`list-group-item d-flex justify-content-between align-items-center ${selectedUserId === user.id ? 'bg-light' : ''}`}
// //             >
// //               <div>
// //                 <h6 className="mb-0">{user.name}</h6>
// //                 <small className="text-muted">{user.role}</small>
// //               </div>
// //               <button
// //                 className="btn btn-sm btn-outline-primary"
// //                 onClick={() => setSelectedUserId(user.id)}
// //               >
// //                 Details
// //               </button>
// //             </div>
// //           ))}
// //         </div>
// //       </div>

// //       {/* User Details */}
// //       <div className="col-md-7">
// //         {userDetails && <UserDetailsCard user={userDetails} />}
// //       </div>
// //     </div>
// //   );
// // }

// // export default AdminUsers;


// import React, { useEffect, useState } from 'react';
// import axios from 'axios';

// function AdminUsers() {
//   const [users, setUsers] = useState([]);
//   const [selectedUser, setSelectedUser] = useState(null);
//   const API_BASE_URL = 'http://localhost:8081';

//   useEffect(() => {
//     axios.get(`${API_BASE_URL}/admin/users`)
//       .then(async (res) => {
//         const usersBasic = res.data; // array with userId and role

//         // Fetch details for each user in parallel
//         const usersWithNames = await Promise.all(
//           usersBasic.map(async (user) => {
//             try {
//               const detailRes = await axios.get(`${API_BASE_URL}/admin/user-details/${user.userId}`);
//               return {
//                 ...user,
//                 name: detailRes.data.name || 'N/A', // fallback if name missing
//                 profile: detailRes.data,
//               };
//             } catch (err) {
//               console.error(`Error fetching details for user ${user.userId}`, err);
//               return { ...user, name: 'Unknown', profile: null };
//             }
          
//           })
//         );
//         console.log(users);

//         setUsers(usersWithNames);
//         if (usersWithNames.length > 0) setSelectedUser(usersWithNames[0]);
//       })
//       .catch(err => console.error('Error fetching users', err));
//   }, []);

//   const handleSelectUser = (user) => {
//     setSelectedUser(user);
//   };

//   return (
//     <div className="row">
//       {/* User list on the left */}
//       <div className="col-md-5 border-end">
//         <h5 className="my-3">All Users</h5>
//         <div className="list-group">
//           {users.map(user => (
//             <div
//               key={user.userId}
//               className={`list-group-item list-group-item-action d-flex justify-content-between align-items-center ${selectedUser?.id === user.id ? 'active' : ''}`}
//               onClick={() => handleSelectUser(user)}
//             >
//               <div>
//                 <h6 className="mb-0">{user.name}</h6>
//                 <small className="text-muted">{user.role}</small>
//               </div>
//               <button className="btn btn-sm btn-outline-primary">Details</button>
//             </div>
//           ))}
//         </div>
//       </div>

//       {/* User details on the right */}
//       <div className="col-md-7 p-3">
//         {selectedUser ? (
//           <div className="card shadow-sm">
//             <div className="card-body">
//               <h5 className="card-title">{selectedUser.name}</h5>
//               <p className="card-text">
//                 <strong>Role:</strong> {selectedUser.role} <br />
//                 <strong>Email:</strong> {selectedUser.profile?.email || 'N/A'} <br />
//                 {/* Add more fields conditionally */}
//                 {selectedUser.role === 'candidate' && (
//                   <>
//                     <strong>Skills:</strong> {selectedUser.profile?.skills?.map(skill => skill.name).join(', ') || 'N/A'}
//                   </>
//                 )}
//                 {selectedUser.role === 'recruiter' && (
//                   <>
//                     <strong>Company:</strong> {selectedUser.profile?.companyName || 'N/A'}
//                   </>
//                 )}
//               </p>
//             </div>
//           </div>
//         ) : (
//           <p>Select a user to view details</p>
//         )}
//       </div>
//     </div>
//   );
// }

// export default AdminUsers;

import React, { useState, useEffect } from 'react';
import axios from 'axios';

function AdminUsers() {
   const [users, setUsers] = useState([]);
  const [selectedUser, setSelectedUser] = useState(null);
  const API_BASE_URL = 'http://localhost:8081';

  useEffect(() => {
    axios.get(`${API_BASE_URL}/admin/users`)
      .then(async (res) => {
        const usersBasic = res.data; // array with userId and role

        // Fetch details for each user in parallel
        const usersWithNames = await Promise.all(
          usersBasic.map(async (user) => {
            try {
              const detailRes = await axios.get(`${API_BASE_URL}/admin/user-details/${user.userId}`);
              return {
                ...user,
                name: detailRes.data.name || 'N/A', // fallback if name missing
                profile: detailRes.data,
              };
            } catch (err) {
              console.error(`Error fetching details for user ${user.userId}`, err);
              return { ...user, name: 'Unknown', profile: null };
            }
          
          })
        );
        console.log(users);

        setUsers(usersWithNames);
        if (usersWithNames.length > 0) setSelectedUser(usersWithNames[0]);
      })
      .catch(err => console.error('Error fetching users', err));
  }, []);

  const handleSelectUser = (user) => {
    setSelectedUser(user);
  };

  return (
    <div className="container mt-4">
      <div className="row">
        {/* User list */}
        <div className="col-md-5">
          <h5 className="mb-3">All Users</h5>
          {users.map(user => (
            <div
              key={user.userId}
              className="card mb-3 shadow-sm"
            >
              <div className="card-body d-flex justify-content-between align-items-center">
                <div>
                  <h6 className="mb-1">{user.name || 'Unknown User'}</h6>
                  <small className="text-muted text-capitalize">{user.role}</small>
                </div>
                <button
                  className="btn btn-outline-primary btn-sm"
                  onClick={() => handleSelectUser(user)}
                >
                  Details
                </button>
              </div>
            </div>
          ))}
        </div>

        {/* User Details */}
        <div className="col-md-7">
          {selectedUser ? (
            <div className="card shadow-sm">
              <div className="card-body">
                <h5 className="card-title">{selectedUser.name || 'N/A'}</h5>
                <p className="card-text">
                  <strong>Role:</strong> {selectedUser.role} <br />
                  <strong>Email:</strong> {selectedUser.profile?.email || 'N/A'} <br />
                  {selectedUser.role === 'candidate' && (
                    <>
                      <strong>Skills:</strong> {selectedUser.profile?.skills?.map(skill => skill.name).join(', ') || 'N/A'}
                    </>
                  )}
                  {selectedUser.role === 'recruiter' && (
                    <>
                      <strong>Company:</strong> {selectedUser.profile?.companyName || 'N/A'}
                    </>
                  )}
                </p>
              </div>
            </div>
          ) : (
            <div className="text-muted">Select a user to view details</div>
          )}
        </div>
      </div>
    </div>
  );
}

export default AdminUsers;
