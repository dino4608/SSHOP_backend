import { Flex, theme } from "antd";
import { Content } from "antd/es/layout/layout";

const ProductRatings = () => {
    const { token: { colorBgContainer, borderRadiusLG } } = theme.useToken();

    return (
        <>
            <Flex>
                <h1 className='dmon-title-header'>Product Ratings</h1>
            </Flex>
            <Content
                style={{
                    padding: 24,
                    margin: 0,
                    minHeight: 400,
                    background: colorBgContainer,
                    borderRadius: borderRadiusLG,
                }}
            >
                Product Ratings
            </Content>
        </>
    );
};

export default ProductRatings;