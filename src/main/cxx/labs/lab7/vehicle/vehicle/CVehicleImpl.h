#pragma once
#include "IVehicle.h"
#include <stdexcept>
#include <vector>

template <typename Base, typename Passenger>
class CVehicleImpl : public Base
{
    const size_t m_placeCount;
    std::vector<std::shared_ptr<Passenger>> m_passengers{};

    void CheckPassengersBounds(size_t index) const
    {
        if (index >= m_placeCount)
        {
            throw std::logic_error("Vehicle is full!");
        }
    };

public:
    CVehicleImpl(size_t placeCount)
        : m_placeCount(placeCount){};

    ~CVehicleImpl() = default;

    void AddPassenger(std::shared_ptr<Passenger> pPassenger)
    {
        CheckPassengersBounds(m_passengers.size());
        m_passengers.emplace_back(pPassenger);
    };

    Passenger const& GetPassenger(size_t index) const override
    {
        CheckPassengersBounds(index);
        return static_cast<Passenger const&>(*m_passengers.at(index));
    };

    size_t GetPassengerCount() const override
    {
        return m_passengers.size();
    };

    bool IsEmpty() const override
    {
        return m_passengers.empty();
    };

    bool IsFull() const override
    {
        return m_passengers.size() == m_placeCount;
    };

    void RemoveAllPassengers() override
    {
        m_passengers.clear();
    };

    void RemovePassenger(size_t index) override
    {
        CheckPassengersBounds(index);
        m_passengers.erase(m_passengers.begin() + index);
    };

    size_t GetPlaceCount() const override
    {
        return m_placeCount;
    };
};
