'use client';
// import { logoutUser } from '@/actions/auth';
// import HeaderSearchBar from '@/components/layout/HeaderSearchBar';
// import { useCartStore } from '@/stores/cart-store';
// import { User } from '@prisma/client';
import Link from 'next/link';
import { useRouter } from 'next/navigation';
import React, { useEffect, useState } from 'react';
import { useShallow } from 'zustand/shallow';

const AnnouncementBar = () => {
    return (
        <div className='w-full bg-black py-2'>
            <div className='container mx-auto flex items-center justify-center px-8'>
                <span className='text-center text-sm font-medium tracking-wide text-white'>
                    FREE SHIPPING ON ORDERS OVER $15.00 • FREE RETURNS
                </span>
            </div>
        </div>
    );
}

const Header: React.FC = () => {
    //Note:
    //amount the component => init states
    //change states => re-render the component
    //unmount the component => destroy states
    const [isOpen, setIsOpen] = useState<boolean>(true);
    const [prevScrollY, setPrevScrollY] = useState<number>(0);

    //Note:
    //useEffect always runs when mounting
    //useEffect with no dependencies re-runs whe the component re-render
    //useEffect with a multi dependencies array re-runs when the dependencies change
    //useEffect with an empty dependencies array doesn't re-run
    useEffect(() => {
        const handleScroll = () => {
            //Note:
            //Although states are updated, handleScroll uses the states at its register time.
            //To use the updated states, please re-register it, please re-run useEffect
            const currentScrollY = window.scrollY;
            const scrolledUp = currentScrollY < prevScrollY;

            if (scrolledUp) {
                setIsOpen(true);
            } else if (currentScrollY > 100) {
                setIsOpen(false);
            }

            setPrevScrollY(currentScrollY);
        };

        setPrevScrollY(window.scrollY);

        //Note: Register an side effect on window when the component amounts and useEffect re-runs
        window.addEventListener('scroll', handleScroll);

        return () => {
            //Note:
            //Clean the side effect on window when the component unmounts or useEffect re-runs
            //When useEffect re-runs, the code inside return runs firstly, then the code above return runs
            //Therefore, the clean up before the register again
            window.removeEventListener('scroll', handleScroll);
        };
    }, [prevScrollY]);

    return (
        <header className='w-full sticky top-0 z-50'>
            <div className={`w-full transform transition-transform duration-300 ease-in-out ${isOpen ? 'translate-y-0' : '-translate-y-full'}`}>
                <AnnouncementBar />
            </div>

            <div className='w-full flex justify-between items-center py-3 sm:py-4 bg-white/80 shadow-sm border-b border-gray-100 backdrop-blur-sm'>
                <div className='flex justify-between items-center container mx-auto px-8'>
                    <div className='flex flex-1 justify-start items-center gap-4 sm:gap-6'>
                        <button className='text-gray-700 hover:text-gray-900 md:hidden'>
                            <svg xmlns='http://www.w3.org/2000/svg' className='h-5 w-5 sm:h-6 sm:w-6' fill='none' viewBox='0 0 24 24' stroke='currentColor'>
                                <path strokeLinecap='round' strokeLinejoin='round' strokeWidth={2} d='M4 6h16M4 12h16M4 18h16' />
                            </svg>
                        </button>

                        <nav className='hidden md:flex gap-4 lg:gap-6 text-sm font-medium'>
                            {/* {categorySelector} */}
                            <Link href='#'>Shop</Link>
                            <Link href='#'>New Arrivals</Link>
                            <Link href='#'>Sale</Link>
                        </nav>
                    </div>

                    <Link href='#' className='absolute left-1/2 -translate-x-1/2'>
                        <span className='text-xl sm:text-2xl font-bold tracking-tight'>DEAL</span>
                    </Link>

                    <div className='flex flex-1 justify-end items-center gap-2 sm:gap-4'>
                        {/* <HeaderSearchBar /> */}
                        <div className='relative'>
                            <div className='absolute inset-y-0 left-0 pl-2 flex items-center pointer-events-none'>
                                <svg xmlns='http://www.w3.org/2000/svg' className='h-4 w-4 text-gray-400' fill='none' viewBox='0 0 24 24' stroke='currentColor'>
                                    <path strokeLinecap='round' strokeLinejoin='round' strokeWidth={2} d='M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z' />
                                </svg>
                            </div>
                            <input
                                type='text'
                                name='query'
                                placeholder='Search...'
                                className='w-32 pl-8 pr-2 py-1 text-sm border border-gray-200 rounded-md focus:ring-1 focus:ring-black focus:border-transparent transition-colors'
                            />
                        </div>

                        {/* {user ? (
                            <div className='flex items-center gap-2 sm:gap-4'>
                                <span className='text-sm text-gray-700 hidden md:block'>{user.email}</span>
                                <Link
                                    href='#'
                                    className='text-xs sm:text-sm font-medium text-gray-700 hover:text-gray-900'
                                    onClick={async (e) => {
                                        e.preventDefault();
                                        await logoutUser();
                                        router.refresh();
                                    }}
                                >
                                    Sign Out
                                </Link>
                            </div>
                        ) : (
                            <React.Fragment>
                                <Link href='/auth/sign-in' className='text-xs sm:text-sm font-medium text-gray-700 hover:text-gray-900'>
                                    Sign In
                                </Link>
                                <Link href='/auth/sign-up' className='text-xs sm:text-sm font-medium text-gray-700 hover:text-gray-900'>
                                    Sign Up
                                </Link>
                            </React.Fragment>
                        )} */}
                        <Link href='/auth/sign-in' className='text-xs sm:text-sm font-medium text-gray-700 hover:text-gray-900'>
                            Sign In
                        </Link>
                        <Link href='/auth/sign-up' className='text-xs sm:text-sm font-medium text-gray-700 hover:text-gray-900'>
                            Sign Up
                        </Link>

                        <button onClick={() => open()} className='text-gray-700 hover:text-gray-900 relative'>
                            <svg xmlns='http://www.w3.org/2000/svg' className='h-5 w-5 sm:h-6 sm:w-6' fill='none' viewBox='0 0 24 24' stroke='currentColor'>
                                <path strokeLinecap='round' strokeLinejoin='round' strokeWidth={2} d='M16 11V7a4 4 0 00-8 0v4M5 9h14l1 12H4L5 9z' />
                            </svg>
                            <span className='absolute -top-1 -right-1 bg-black text-white text-[10px] sm:text-xs w-3.5 h-3.5 sm:w-4 sm:h-4 rounded-full flex items-center justify-center'>
                                {/* {getTotalItems()} */}0
                            </span>
                        </button>
                    </div>
                </div>
            </div>
        </header>
    );
};

export default Header;