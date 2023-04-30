import React from "react";
import { Link } from "react-router-dom";
import { ReactComponent as ProfileIcon } from "./profile_icon.svg";

export default function ProfileLink() {
  return (
    <Link to="/profile" style={{ width: "30px" }}>
      <ProfileIcon />
    </Link>
  );
}
