import { getCurrentSession } from '@/actions/auth';
import { redirect } from 'next/navigation';
import React from 'react';

const SignUpPage: React.FC = async () => {
    const { user } = await getCurrentSession();

    if (user) {
        return redirect('/');
    }

    return (
        <div>
            Sign Up Page
        </div>
    );
};

export default SignUpPage;