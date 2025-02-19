import React from 'react';

const AnnouncementBar = () => {
    return (
        <div className='w-full bg-black py-2'>
            <div>
                <span>
                    FREE SHIPPING ON ORDERS OVER $15.00 • FREE RETURNS
                </span>
            </div>
        </div>
    );
}

const Header = () => {
    return (
        <header>
            <div>
                <AnnouncementBar />
            </div>
        </header>
    );
};

export default Header;