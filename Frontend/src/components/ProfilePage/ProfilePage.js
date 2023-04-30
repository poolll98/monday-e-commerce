import React from "react";
import UserData from "../../mocks/testseller";
import productData from "../../mocks/products";
import "./ProfilePage.css";
import LoginGuard from "../LoginGuard";
import { useState } from "react";
import { Link } from "react-router-dom";

function ProfileRightBoxDisplayMode({thisUser, setIsEditMode}) {
    return (
        <div className="right-box">
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
                <p>{thisUser.lastname || `\u00A0`}</p>
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
                <p className="profile-field-title">{thisUser.isseller ? "Payment:" : "Address:"}</p>
                <p>{thisUser.isseller ? thisUser.payment : thisUser.address || `\u00A0`}</p>
            </div>
            <div class="profile-field">
                <button className="blue-button" onClick={() => setIsEditMode(true)}>
                Edit
                </button>
            </div>
        </div>
    );
}

function ProfileRightBoxEditMode({thisUser, setIsEditMode}) {
    return (
        <div className="right-box">
            <div class="profile-field">
                <p className="profile-field-title">Username:    </p>
                <input type="text" defaultValue={thisUser.username} />
            </div>
            <div class="profile-field">
                <p className="profile-field-title">First Name:  </p>
                <input type="text" defaultValue={thisUser.firstname} />
            </div>
            <div class="profile-field">
                <p className="profile-field-title">Last Name:   </p>
                <input type="text" defaultValue={thisUser.lastname} />
            </div>
            <div class="profile-field">
                <p className="profile-field-title">E-mail:      </p>
                <input type="text" defaultValue={thisUser.email} />
            </div>
            <div class="profile-field">
                <p className="profile-field-title">Phone number:</p>
                <input type="text" defaultValue={thisUser.phone} />
            </div>
            <div class="profile-field">
                <p className="profile-field-title">{thisUser.isseller ? "Payment:" : "Address:"}</p>
                <input type="text" defaultValue={thisUser.isseller ? thisUser.payment : thisUser.address} />
            </div>
            <div class="profile-field">
                <button className="blue-button" onClick={() => setIsEditMode(false)}>
                Save
                </button>
                {/* TODO save real data */}
            </div>
        </div>
    );
}

function ProfileRightBoxProductList({thisUser}) {
    const products = productData.map((product) => {
        return (
            <div class="profile-field">
                <div className="profile-field-title" key={product.id}>
                    <Link to={`/products/${product.id}`}>{product.name}</Link>
                </div>
                <button className="white-button" onClick={() => linkToEditProduct()}>
                    Edit
                </button>
            </div>
        );
      });

    return (
        <div className="right-box">
            {products}
            <div class="profile-field">
                <button className="blue-button" onClick={() => uploadProduct()}>
                    Upload One Product
                </button>
            </div>
        </div>
    );
}

function linkToEditProduct() {
// TODO: linkToEditProduct.
}

function uploadProduct() {
// TODO: linkToEditProduct.
}
  
export default function ProfilePage() {
    const thisUser = UserData;
    const [isEditMode, setIsEditMode] = useState(false);
    const [isProductList, setIsProductList] = useState(false);
  
    /* functional component => render JSX */
    return (
        <LoginGuard>
            <div className="profile-wrapper">
                <div className="profile-header">
                    <h3>{thisUser.isseller ? "Seller" : "User"} Profile</h3>
                </div>
    
                <div className="profile-body">
                    <div className="left-box">
                        <div class="profile-field">
                            <button className="left-button" onClick={() => setIsProductList(false)}>
                                Account Information
                            </button>
                        </div>
                        <div class="profile-field">
                            {thisUser.isseller && (
                                <button className="left-button" onClick={() => setIsProductList(true)}>
                                    Product List
                                </button>
                            )}
                        </div>
                    </div>
                    {isProductList ? (
                        <ProfileRightBoxProductList thisUser={thisUser}/>
                        ) : (
                            isEditMode ? (
                                <ProfileRightBoxEditMode thisUser={thisUser} setIsEditMode={setIsEditMode}/>
                                ) : (
                                <ProfileRightBoxDisplayMode thisUser={thisUser} setIsEditMode={setIsEditMode}/>
                            )
                    )}
                </div>
            </div>
        </LoginGuard>
    );
}
  