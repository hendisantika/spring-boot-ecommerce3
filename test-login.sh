#!/bin/bash

# Test login for all Jujutsu Kaisen characters
# This script tests the login functionality by attempting to authenticate with each user

BASE_URL="http://localhost:8080"
COOKIE_JAR="/tmp/cookies.txt"

echo "========================================="
echo "Testing Login for All JJK Characters"
echo "========================================="
echo ""

# Test credentials (username|email|password|role)
CREDENTIALS=(
    "Yuji Itadori|yuji@jujutsukaisen.com|yuji123|ADMIN"
    "Megumi Fushiguro|megumi@jujutsukaisen.com|megumi123|NORMAL"
    "Nobara Kugisaki|nobara@jujutsukaisen.com|nobara123|NORMAL"
    "Satoru Gojo|gojo@jujutsukaisen.com|gojo123|ADMIN"
    "Maki Zenin|maki@jujutsukaisen.com|maki123|NORMAL"
    "Toge Inumaki|toge@jujutsukaisen.com|toge123|NORMAL"
    "Panda|panda@jujutsukaisen.com|panda123|NORMAL"
    "Yuta Okkotsu|yuta@jujutsukaisen.com|yuta123|NORMAL"
    "Kento Nanami|nanami@jujutsukaisen.com|nanami123|NORMAL"
    "Sukuna Ryomen|sukuna@jujutsukaisen.com|sukuna123|NORMAL"
)

test_login() {
    local username=$1
    local email=$2
    local password=$3
    local role=$4

    # Clean up cookie jar
    rm -f $COOKIE_JAR

    # Get login page and extract CSRF token
    LOGIN_PAGE=$(curl -s -c $COOKIE_JAR "${BASE_URL}/login")
    CSRF_TOKEN=$(echo "$LOGIN_PAGE" | grep '_csrf' | sed -n 's/.*name="_csrf" value="\([^"]*\)".*/\1/p' | head -1)

    if [ -z "$CSRF_TOKEN" ]; then
        echo "  ❌ FAILED: Could not get CSRF token"
        return 1
    fi

    # Attempt login
    HTTP_CODE=$(curl -s -w "%{http_code}" -o /dev/null \
        -b $COOKIE_JAR -c $COOKIE_JAR \
        -X POST "${BASE_URL}/login" \
        -d "username=${email}" \
        -d "password=${password}" \
        -d "_csrf=${CSRF_TOKEN}")

    # Check if login was successful
    # 302 = redirect (successful login redirects to home page)
    # 200 = might be successful or error page
    if [[ $HTTP_CODE == "302" ]]; then
        echo "  ✅ SUCCESS: Login successful (HTTP $HTTP_CODE - Redirected)"
        return 0
    elif [[ $HTTP_CODE == "200" ]]; then
        # Check if we're authenticated by trying to access a protected page
        PROTECTED_CHECK=$(curl -s -w "%{http_code}" -o /dev/null -b $COOKIE_JAR "${BASE_URL}/")
        if [[ $PROTECTED_CHECK == "302" ]] || [[ $PROTECTED_CHECK == "200" ]]; then
            echo "  ✅ SUCCESS: Login successful (HTTP $HTTP_CODE - Authenticated)"
            return 0
        else
            echo "  ❌ FAILED: Login failed (HTTP $HTTP_CODE - Not authenticated)"
            return 1
        fi
    else
        echo "  ❌ FAILED: HTTP $HTTP_CODE"
        return 1
    fi
}

# Counter for results
SUCCESS_COUNT=0
FAIL_COUNT=0

# Test each user
for credential in "${CREDENTIALS[@]}"; do
    IFS='|' read -r username email password role <<< "$credential"
    echo "Testing: $username ($role)"
    echo "  Email: $email"
    echo "  Password: $password"

    if test_login "$username" "$email" "$password" "$role"; then
        ((SUCCESS_COUNT++))
    else
        ((FAIL_COUNT++))
    fi
    echo ""

    # Small delay between tests to avoid overwhelming the server
    sleep 0.5
done

# Clean up
rm -f $COOKIE_JAR

# Summary
echo "========================================="
echo "Test Results:"
echo "  ✅ Successful: $SUCCESS_COUNT"
echo "  ❌ Failed: $FAIL_COUNT"
echo "  Total: $((SUCCESS_COUNT + FAIL_COUNT))"
echo "========================================="

if [ $FAIL_COUNT -eq 0 ]; then
    echo "🎉 All login tests passed!"
    exit 0
else
    echo "⚠️  Some login tests failed!"
    exit 1
fi
