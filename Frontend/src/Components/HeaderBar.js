import React from 'react';
import { Link } from 'react-router-dom';

export default function HeaderBar() {
    const colors = {'color': 'blue'}
    return (
        <div style={{...colors, 'textAlign': "center"}}>
            <Link to="/">MarketMate</Link>
            <h3>Here be the header</h3>
        </ div>);
}