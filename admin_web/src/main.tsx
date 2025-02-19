import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import { BrowserRouter, Routes, Route, Navigate } from "react-router";
import LayoutPage from './pages/base/layout.page';
import './style.scss';
import HomePage from './pages/home/home.page';
import ManageOrders from './pages/order/manage-orders.page';
import ManageCancellations from './pages/order/manage-cancellations.page';
import ManageProducts from './pages/product/manage-products.page';
import ProductRatings from './pages/product/product-ratings.page';
import LoginPage from './pages/account/login/login.page';
import PrivatePage from './pages/base/private.page';
import PublicPage from './pages/base/public.page';

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <BrowserRouter>
      <Routes>
        {/* public routes */}
        <Route element={<PublicPage />}>
          {/* account */}
          <Route path='access'>
            <Route index element={<Navigate to='/access/login' />} />
            <Route path='login' element={<LoginPage />} />
          </Route>
        </Route>

        {/* private routes */}
        <Route element={<PrivatePage />}>
          <Route element={<LayoutPage />}>
            {/* homepage */}
            <Route index element={<Navigate to='/homepage' />} />
            <Route path='homepage' element={<HomePage />} />

            {/* order */}
            <Route path='order'>
              <Route index element={<Navigate to='/order/manage' />} />
              <Route path='manage' element={<ManageOrders />} />
              <Route path='cancellation' element={<ManageCancellations />} />
            </Route>

            {/* product */}
            <Route path='product'>
              <Route index element={<Navigate to='/product/manage' />} />
              <Route path='manage' element={<ManageProducts />} />
              <Route path='rating' element={<ProductRatings />} />
            </Route>

          </Route>
        </Route>
      </Routes>
    </BrowserRouter>
  </StrictMode >
);
