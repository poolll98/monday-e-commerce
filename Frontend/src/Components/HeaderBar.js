import React from 'react';
import { Link } from 'react-router-dom';

export default function HeaderBar() {
    return (
        <div style={{'textAlign': "center"}}>
            <Link to="/">MarketMate</Link>
            <h3>Here be the header</h3>
        </ div>);
}