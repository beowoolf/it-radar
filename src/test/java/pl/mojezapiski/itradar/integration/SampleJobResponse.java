package pl.mojezapiski.itradar.integration;

public interface SampleJobResponse {

    default String bodyWithTwoJobsJson() {
        return """
                [
                    {
                        "title": "Backend Developer",
                        "street": "Centrum",
                        "city": "Katowice",
                        "country_code": "PL",
                        "address_text": "Centrum, Katowice",
                        "marker_icon": "go",
                        "workplace_type": "remote",
                        "company_name": "Gamesture Sp. z o.o.",
                        "company_url": "http://gamesture.com",
                        "company_size": "80",
                        "experience_level": "mid",
                        "latitude": "50.2648919",
                        "longitude": "19.0237815",
                        "published_at": "2023-04-19T11:00:14.909Z",
                        "remote_interview": true,
                        "open_to_hire_ukrainians": true,
                        "id": "gamesture-sp-z-o-o-backend-developer-katowice",
                        "display_offer": false,
                        "employment_types": [
                            {
                                "type": "permanent",
                                "salary": {
                                    "from": 8000,
                                    "to": 16000,
                                    "currency": "pln"
                                }
                            }
                        ],
                        "company_logo_url": "https://bucket.justjoin.it/offers/company_logos/thumb/9f1d514b22d4560f196b98981f9752c5d5cc44ea.png?1680604189",
                        "skills": [
                            {
                                "name": "Python",
                                "level": 1
                            },
                            {
                                "name": "SQL",
                                "level": 3
                            },
                            {
                                "name": "Golang",
                                "level": 3
                            }
                        ],
                        "remote": true,
                        "multilocation": [
                            {
                                "city": "Katowice",
                                "street": "Centrum",
                                "slug": "gamesture-sp-z-o-o-backend-developer-katowice"
                            }
                        ],
                        "way_of_apply": "redirect"
                    },
                    {
                        "title": "Backend Developer",
                        "street": "Centrum",
                        "city": "Wrocław",
                        "country_code": "PL",
                        "address_text": "Centrum, Wrocław",
                        "marker_icon": "go",
                        "workplace_type": "remote",
                        "company_name": "Gamesture Sp. z o.o.",
                        "company_url": "http://gamesture.com",
                        "company_size": "80",
                        "experience_level": "mid",
                        "latitude": "51.1078852",
                        "longitude": "17.0385376",
                        "published_at": "2023-04-19T11:00:14.909Z",
                        "remote_interview": true,
                        "open_to_hire_ukrainians": true,
                        "id": "gamesture-sp-z-o-o-backend-developer-wroclaw",
                        "display_offer": false,
                        "employment_types": [
                            {
                                "type": "permanent",
                                "salary": {
                                    "from": 8000,
                                    "to": 16000,
                                    "currency": "pln"
                                }
                            }
                        ],
                        "company_logo_url": "https://bucket.justjoin.it/offers/company_logos/thumb/4d9a5369b294d3b34782e26178c82a78836d3073.png?1680604238",
                        "skills": [
                            {
                                "name": "Python",
                                "level": 1
                            },
                            {
                                "name": "SQL",
                                "level": 3
                            },
                            {
                                "name": "Golang",
                                "level": 3
                            }
                        ],
                        "remote": true,
                        "multilocation": [
                            {
                                "city": "Wrocław",
                                "street": "Centrum",
                                "slug": "gamesture-sp-z-o-o-backend-developer-wroclaw"
                            }
                        ],
                        "way_of_apply": "redirect"
                    }
                ]
                """.trim();
    }

    default String bodyWithOneJobJson() {
        return """
                [
                    {
                        "title": "Backend Developer",
                        "street": "Centrum",
                        "city": "Wrocław",
                        "country_code": "PL",
                        "address_text": "Centrum, Wrocław",
                        "marker_icon": "go",
                        "workplace_type": "remote",
                        "company_name": "Gamesture Sp. z o.o.",
                        "company_url": "http://gamesture.com",
                        "company_size": "80",
                        "experience_level": "mid",
                        "latitude": "51.1078852",
                        "longitude": "17.0385376",
                        "published_at": "2023-04-19T11:00:14.909Z",
                        "remote_interview": true,
                        "open_to_hire_ukrainians": true,
                        "id": "gamesture-sp-z-o-o-backend-developer-wroclaw",
                        "display_offer": false,
                        "employment_types": [
                            {
                                "type": "permanent",
                                "salary": {
                                    "from": 8000,
                                    "to": 16000,
                                    "currency": "pln"
                                }
                            }
                        ],
                        "company_logo_url": "https://bucket.justjoin.it/offers/company_logos/thumb/4d9a5369b294d3b34782e26178c82a78836d3073.png?1680604238",
                        "skills": [
                            {
                                "name": "Python",
                                "level": 1
                            },
                            {
                                "name": "SQL",
                                "level": 3
                            },
                            {
                                "name": "Golang",
                                "level": 3
                            }
                        ],
                        "remote": true,
                        "multilocation": [
                            {
                                "city": "Wrocław",
                                "street": "Centrum",
                                "slug": "gamesture-sp-z-o-o-backend-developer-wroclaw"
                            }
                        ],
                        "way_of_apply": "redirect"
                    }
                ]
                """.trim();
    }

    default String bodyWithFourJobsJson() {
        return """
                [
                    {
                        "title": "CS Cloud Compliance Expert",
                        "street": "City Center",
                        "city": "Gdańsk",
                        "country_code": "PL",
                        "address_text": "City Center, Gdańsk",
                        "marker_icon": "security",
                        "workplace_type": "remote",
                        "company_name": "emagine Polska",
                        "company_url": "http://www.emagine.pl",
                        "company_size": "1400+",
                        "experience_level": "mid",
                        "latitude": "54.3520252",
                        "longitude": "18.6466384",
                        "published_at": "2023-04-24T10:13:00.000Z",
                        "remote_interview": true,
                        "open_to_hire_ukrainians": false,
                        "id": "emagine-polska-cs-cloud-compliance-expert-gdansk",
                        "display_offer": false,
                        "employment_types": [
                            {
                                "type": "b2b",
                                "salary": {
                                    "from": 25200,
                                    "to": 28560,
                                    "currency": "pln"
                                }
                            }
                        ],
                        "company_logo_url": "https://bucket.justjoin.it/offers/company_logos/thumb/abfe3aabdcd4dec002c88d5e12a2cac98aa483bc.jpg?1682331219",
                        "skills": [
                            {
                                "name": "Cloud",
                                "level": 4
                            }
                        ],
                        "remote": true,
                        "multilocation": [
                            {
                                "city": "Gdańsk",
                                "street": "City Center",
                                "slug": "emagine-polska-cs-cloud-compliance-expert-gdansk"
                            }
                        ],
                        "way_of_apply": "form"
                    },
                    {
                        "title": "Analityk IT - Zespół Rozwoju IPKO Biznes",
                        "street": "Centrum",
                        "city": "Lublin",
                        "country_code": "PL",
                        "address_text": "Centrum, Lublin",
                        "marker_icon": "analytics",
                        "workplace_type": "partly_remote",
                        "company_name": "PKO Bank Polski",
                        "company_url": "https://www.pkobp.pl/kariera/",
                        "company_size": "22000",
                        "experience_level": "mid",
                        "latitude": "51.2464536",
                        "longitude": "22.5684463",
                        "published_at": "2023-04-24T09:00:17.954Z",
                        "remote_interview": true,
                        "open_to_hire_ukrainians": false,
                        "id": "pko-bank-polski-analityk-it-zespol-rozwoju-ipko-biznes-lublin",
                        "display_offer": false,
                        "employment_types": [
                            {
                                "type": "permanent",
                                "salary": null
                            }
                        ],
                        "company_logo_url": "https://bucket.justjoin.it/offers/company_logos/thumb/d36e3b4e234984955d6960a05fad74a98e532298.png?1681460288",
                        "skills": [
                            {
                                "name": "Analytics",
                                "level": 4
                            }
                        ],
                        "remote": false,
                        "multilocation": [
                            {
                                "city": "Lublin",
                                "street": "Centrum",
                                "slug": "pko-bank-polski-analityk-it-zespol-rozwoju-ipko-biznes-lublin"
                            }
                        ],
                        "way_of_apply": "redirect"
                    },
                    {
                        "title": "Backend Developer",
                        "street": "Centrum",
                        "city": "Katowice",
                        "country_code": "PL",
                        "address_text": "Centrum, Katowice",
                        "marker_icon": "go",
                        "workplace_type": "remote",
                        "company_name": "Gamesture Sp. z o.o.",
                        "company_url": "http://gamesture.com",
                        "company_size": "80",
                        "experience_level": "mid",
                        "latitude": "50.2648919",
                        "longitude": "19.0237815",
                        "published_at": "2023-04-19T11:00:14.909Z",
                        "remote_interview": true,
                        "open_to_hire_ukrainians": true,
                        "id": "gamesture-sp-z-o-o-backend-developer-katowice",
                        "display_offer": false,
                        "employment_types": [
                            {
                                "type": "permanent",
                                "salary": {
                                    "from": 8000,
                                    "to": 16000,
                                    "currency": "pln"
                                }
                            }
                        ],
                        "company_logo_url": "https://bucket.justjoin.it/offers/company_logos/thumb/9f1d514b22d4560f196b98981f9752c5d5cc44ea.png?1680604189",
                        "skills": [
                            {
                                "name": "Python",
                                "level": 1
                            },
                            {
                                "name": "SQL",
                                "level": 3
                            },
                            {
                                "name": "Golang",
                                "level": 3
                            }
                        ],
                        "remote": true,
                        "multilocation": [
                            {
                                "city": "Katowice",
                                "street": "Centrum",
                                "slug": "gamesture-sp-z-o-o-backend-developer-katowice"
                            }
                        ],
                        "way_of_apply": "redirect"
                    },
                    {
                        "title": "Backend Developer",
                        "street": "Centrum",
                        "city": "Wrocław",
                        "country_code": "PL",
                        "address_text": "Centrum, Wrocław",
                        "marker_icon": "go",
                        "workplace_type": "remote",
                        "company_name": "Gamesture Sp. z o.o.",
                        "company_url": "http://gamesture.com",
                        "company_size": "80",
                        "experience_level": "mid",
                        "latitude": "51.1078852",
                        "longitude": "17.0385376",
                        "published_at": "2023-04-19T11:00:14.909Z",
                        "remote_interview": true,
                        "open_to_hire_ukrainians": true,
                        "id": "gamesture-sp-z-o-o-backend-developer-wroclaw",
                        "display_offer": false,
                        "employment_types": [
                            {
                                "type": "permanent",
                                "salary": {
                                    "from": 8000,
                                    "to": 16000,
                                    "currency": "pln"
                                }
                            }
                        ],
                        "company_logo_url": "https://bucket.justjoin.it/offers/company_logos/thumb/4d9a5369b294d3b34782e26178c82a78836d3073.png?1680604238",
                        "skills": [
                            {
                                "name": "Python",
                                "level": 1
                            },
                            {
                                "name": "SQL",
                                "level": 3
                            },
                            {
                                "name": "Golang",
                                "level": 3
                            }
                        ],
                        "remote": true,
                        "multilocation": [
                            {
                                "city": "Wrocław",
                                "street": "Centrum",
                                "slug": "gamesture-sp-z-o-o-backend-developer-wroclaw"
                            }
                        ],
                        "way_of_apply": "redirect"
                    }
                ]
                """.trim();
    }

    default String bodyWithZeroJobsJson() {
        return "[]";
    }

}
