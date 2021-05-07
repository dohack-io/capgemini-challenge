const baseUrl = process.env.DEV ? 'http://localhost:8081/'
  : 'http://capgemini-loadbalancer-1573441832.eu-central-1.elb.amazonaws.com:8081/';

export async function getAllChallenges() {
  return await fetch(`${baseUrl}challenge/all`, {
    method: 'GET'
  });
}

export async function getCurrentDailyChallenge(username) {
  return await fetch(`${baseUrl}statistics/daily/${username}/current`, {
    method: 'GET'
  });
}

export async function getLeaderboard() {
  return await fetch(`${baseUrl}statistics/leaderboard`);
}

export async function login(username, password) {
  return await fetch(
    `${baseUrl}login?username=${username}&password=${password}`, {
      method: 'POST'
    });
}

export async function submitDailyStatistics(username, coffee, foodScore, dailyChallenge, commutions) {
  return await fetch(`${baseUrl}statistics/daily/create`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8',
    },
    body: JSON.stringify({
      username: username,
      numberOfCoffees: coffee,
      lunchScore: foodScore,
      dailyChallengeCompleted: dailyChallenge,
      dailyCommuteList: commutions.map(commute => {
        return {
          distance: commute.commuteDistance,
          type: commute.commuteType.value
        }
      })
    })
  });
}
