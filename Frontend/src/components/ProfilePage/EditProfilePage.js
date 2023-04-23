import React from "react";
import UserData from "../../mocks/testuser";

import "./ProfilePage.css";
import LoginGuard from "../LoginGuard";
import { Link } from "react-router-dom";

export default function EditProfilePage() {
    const thisUser = UserData;
  
/* functional component => render JSX */
    return (
        <LoginGuard>
            <div className="profile-wrapper">
                <div className="profile-header">
                    <h3>User Profile</h3>
                </div>

                <div className="profile-body">
                    <div class="profile-field">
                        <p className="profile-field-title">Username:</p>
                        <input type="text" defaultValue={thisUser.username} /> 
                    </div>
                    <div class="profile-field">
                        <p className="profile-field-title">First Name:</p>
                        <input type="text" defaultValue={thisUser.firstname} />
                    </div>
                    <div class="profile-field">
                        <p className="profile-field-title">Last Name:</p>
                        <input type="text" defaultValue={thisUser.lastname} />
                    </div>
                    <div class="profile-field">
                        <p className="profile-field-title">E-mail:</p>
                        <input type="text" defaultValue={thisUser.email} />
                    </div>
                    <div class="profile-field">
                        <p className="profile-field-title">Phone number:</p>
                        <input type="text" defaultValue={thisUser.phone} />
                    </div>
                    <div class="profile-field">
                        <p className="profile-field-title">Address:</p>
                        <input type="text" defaultValue={thisUser.address} />
                    </div>
                    <div class="profile-field">
                        <Link to="/profile">
                            <button className="blue-button" >
                                Save
                            </button>
                        </Link>
                    </div>
                </div>
            </div>
        </LoginGuard>
    );
}
