import { Navigate, Outlet } from 'react-router';


const PublicPage = () => {
    const isAuthenticated = Boolean(localStorage.getItem('token'));

    if (isAuthenticated === true) {
        return <Navigate to="/homepage" />;
    }

    return <Outlet />;
};

export default PublicPage;