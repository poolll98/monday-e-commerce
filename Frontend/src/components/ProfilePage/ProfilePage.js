import React from "react";
import UserData from "../../mocks/testuser";

import "./ProfilePage.css";
import LoginGuard from "../LoginGuard";
import { Link } from "react-router-dom";

export default function ProfilePage() {
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
                        <p>{thisUser.username || `\u00A0`}</p>
                    </div>
                    <div class="profile-field">
                        <p className="profile-field-title">First Name:</p>
                        <p>{thisUser.firstname || `\u00A0`}</p>
                    </div>
                    <div class="profile-field">
                        <p className="profile-field-title">Last Name:</p>
                        <p >{thisUser.lastname || `\u00A0`}</p>
                    </div>
                    <div class="profile-field">
                        <p className="profile-field-title">E-mail:</p>
                        <p>{thisUser.email || `\u00A0`}</p>
                    </div>
                    <div class="profile-field">
                        <p className="profile-field-title">Phone number:</p>
                        <p>{thisUser.phone || `\u00A0`}</p>
                    </div>
                    <div class="profile-field">
                        <p className="profile-field-title">Address:</p>
                        <p>{thisUser.address || `\u00A0`}</p>
                    </div>
                    <div class="profile-field">
                        <Link to="/editprofile">
                            <button className="blue-button" >
                                Edit
                            </button>
                        </Link>
                    </div>
                </div>
            </div>
        </LoginGuard>
    );
}
