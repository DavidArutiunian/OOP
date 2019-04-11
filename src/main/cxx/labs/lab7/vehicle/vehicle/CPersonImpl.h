#pragma once
#include <string>

template <typename Base>
class CPersonImpl : public Base
{
    const std::string m_name;

public:
    CPersonImpl(std::string const& name) : m_name(name)
    {
    };

    ~CPersonImpl() = default;

    std::string GetName() const override
    {
        return std::string(m_name);
    };
};
