import { useState, useEffect, useContext } from "react";
import { Link } from "react-router-dom";
import { findByCategory, findByQuery } from "../../services/businessAPI";
import BusinessCard from "./BusinessCard";
import SearchBar from "./SearchBar";
import CategoryList from "./CategoryList";
import AuthContext from "../../contexts/AuthContext";

function Home() {
    const [businesses, setBusinesses] = useState<Array<any>>([]);

    const auth = useContext( AuthContext );

    const handleSearchQuery = async (query: string) => {
        if (query == '' || query.at(0) == ' ') {
            setBusinesses([])
        } else {
            const result = await findByQuery(query);
            if (result.length === 0) {
                setBusinesses([])
            } else {
                setBusinesses(result)
            }    
        }
    };

    const handleSearchCategory = async (category: string) => {
        const result = await findByCategory(category);
        if (result.length === 0) {
            setBusinesses([])
        } else {
        setBusinesses(result)
        }
    };

    // const business = {
    //     businessId: 1,
    //     businessName: "Noah's One Stop Shop",
    //     category: "Professional",
    //     services: [{serviceId: 1, serviceName: "cut hair", cost: 30}, {serviceId: 2, serviceName: "cook food", cost: 20}, {serviceId: 3, serviceName: "other", cost: 10}],
    //     rating: 4.8
    // }

    return (
        <div className="w-screen h-screen bg-gray-400">
            <CategoryList onSearch={handleSearchCategory}></CategoryList>
            <SearchBar onSearch={handleSearchQuery}></SearchBar>
            {/* <BusinessCard key={business.businessId} business={business}></BusinessCard> */}

            <div className="flex flex-wrap items-center alert alert-info">
                {(businesses === null || businesses.length === 0 || businesses.at(0).businessId == 0) ? (
                <p className="m-5 font-bold">
                    No business here
                </p>
                ) : (
                    businesses.map((business) => <BusinessCard key={business.businessId} business={business} />)
                )}
            </div>
        </div>
    );
}

export default Home;
